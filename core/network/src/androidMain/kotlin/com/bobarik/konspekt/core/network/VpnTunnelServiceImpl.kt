package com.bobarik.konspekt.core.network

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Intent
import android.content.pm.ServiceInfo
import android.net.ConnectivityManager
import android.net.ConnectivityManager.NetworkCallback
import android.net.Network
import android.net.NetworkCapabilities
import android.net.NetworkRequest
import android.net.VpnService
import android.os.Build
import android.os.IBinder
import android.os.ParcelFileDescriptor
import androidx.annotation.RequiresApi
import com.bobarik.konspekt.core.network.models.TunnelConfig
import com.bobarik.konspekt.core.network.models.TunnelStatus
import com.bobarik.konspekt.logging.Log
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import outline.Outline
import outline.TCPAndUDPConnectivityResult
import tun2socks.Tun2socks
import tun2socks.Tunnel
import java.io.IOException

@RequiresApi(Build.VERSION_CODES.O)
class VpnTunnelServiceImpl : VpnTunnelService, VpnService() {

  private var tunFd: ParcelFileDescriptor? = null
  private var tunnelConfig: TunnelConfig? = null
  private var remoteDevice: Tunnel? = null
  private val networkConnectivityMonitor: NetworkConnectivityMonitor = NetworkConnectivityMonitor()
  private val tunnelStore: VpnTunnelStore by lazy { VpnTunnelStore(this) }
  private lateinit var notificationBuilder: Notification.Builder

  private val _connectivityState = MutableStateFlow<TunnelStatus>(TunnelStatus.Invalid)
  val connectivityState = _connectivityState.asStateFlow()

  override fun onBind(intent: Intent): IBinder? {
    Log.i("Binding VPN service: $intent")
    if (intent.action == SERVICE_INTERFACE) return super.onBind(intent)
    if (intent.getBooleanExtra(VpnServiceStarter.AutoStartExtra, false)) startLastSuccessfulTunnel()
    return super.onBind(intent)
  }

  /** This is the entrypoint when started by the OS.  */
  override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
    Log.i("Starting VPN service: $intent")
    if (intent != null) {
      // VpnServiceStarter puts AUTOSTART_EXTRA in the intent when the service starts automatically.
      val startedByVpnStarter = intent.getBooleanExtra(VpnServiceStarter.AutoStartExtra, false)
      val startedByAlwaysOn = SERVICE_INTERFACE == intent.action
      if (startedByVpnStarter || startedByAlwaysOn) startLastSuccessfulTunnel()
    }
    return super.onStartCommand(intent, flags, startId)
  }

  override fun onRevoke() {
    Log.i("VPN revoked.")
    _connectivityState.update { TunnelStatus.Disconnected }
    tearDownActiveTunnel()
  }

  override fun onDestroy() {
    Log.i("Destroying VPN service.")
    tearDownActiveTunnel()
  }

  // Autostart
  private fun startLastSuccessfulTunnel() {
    Log.i("Received an auto-connect request, loading last successful tunnel.")
    val tunnel = tunnelStore.load()
    if (tunnel == null) {
      Log.i("Last successful tunnel not found. User not connected at shutdown/install.")
      return
    }
    if (prepare(this) != null) {
      // We cannot prepare the VPN when running as a background service, as it requires UI.
      Log.w("VPN not prepared, aborting auto-connect.")
      return
    }
    try {
      // Start the service in the foreground as per Android 8+ background service execution limits.
      // Requires android.permission.FOREGROUND_SERVICE since Android P.
      startForegroundWithNotification(tunnel.name)
      startTunnel(tunnel, true)
    } catch (e: Exception) {
      Log.e("Failed to retrieve JSON tunnel data", e)
    }
  }

  private fun storeActiveTunnel(config: TunnelConfig, isUdpSupported: Boolean) {
    tunnelStore.save(config)
    tunnelStore.setTunnelStatus(TunnelStatus.Connected(config.id))
    tunnelStore.setIsUdpSupported(isUdpSupported)
  }

  /** Helper method to tear down an active tunnel.  */
  private fun tearDownActiveTunnel() {
    // Stop monitoring network changes.
    try {
      val connectivityManager = getSystemService(ConnectivityManager::class.java)
      connectivityManager.unregisterNetworkCallback(networkConnectivityMonitor)
    } catch (_: Exception) {
    }

    // Stop traffic exchange with remote.
    stopRemoteDevice()

    try {
      // On close, the OS restores the routing and destroys the TUN device.
      tunFd?.close()
    } catch (_: IOException) {
      Log.e("Failed to close the VPN interface file descriptor.")
    } finally {
      tunFd = null
    }

    stopForeground(STOP_FOREGROUND_REMOVE)
    tunnelStore.setTunnelStatus(TunnelStatus.Disconnected)
    tunnelConfig = null
  }

  /** Stops the traffic exchange with the remote device.  */
  @Synchronized
  private fun stopRemoteDevice() {
    remoteDevice?.let { device ->
      if (device.isConnected) device.disconnect()
      remoteDevice = null
    }
  }

  // Foreground service & notifications
  /** Starts the service in the foreground and displays a persistent notification.  */
  private fun startForegroundWithNotification(serverName: String?) {
    if (!::notificationBuilder.isInitialized) {
      notificationBuilder = getNotificationBuilder(serverName)
    }

    notificationBuilder.setContentText("Vpn is connected") // TODO: Use resources?

    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
      startForeground(NotificationServiceId, notificationBuilder.build(), ServiceInfo.FOREGROUND_SERVICE_TYPE_CONNECTED_DEVICE)
    }
  }

  /** Updates the persistent notification to reflect the tunnel status.  */
  private fun updateNotification(status: TunnelStatus) {
    try {
      _connectivityState.update { status }
      if (!::notificationBuilder.isInitialized) return
      val notification = notificationBuilder.setContentText(getString(status.labelId)).build()
      getSystemService(NotificationManager::class.java).notify(NotificationServiceId, notification)
    } catch (_: java.lang.Exception) {
      Log.w("Failed to update persistent notification")
    }
  }

  /** Returns a notification builder with the provided server name.  */
  private fun getNotificationBuilder(serverName: String?): Notification.Builder {
    val launchIntent = packageManager.getLaunchIntentForPackage(application.packageName)
    val mainActivityIntent = PendingIntent.getActivity(this, 0, launchIntent, PendingIntent.FLAG_UPDATE_CURRENT)
    val channel = NotificationChannel(
      /* id = */ NotificationChannelID,
      /* name = */ "Koutline",
      /* importance = */ NotificationManager.IMPORTANCE_LOW,
    )
    val notificationManager = getSystemService(NotificationManager::class.java)
    notificationManager.createNotificationChannel(channel)

    return Notification.Builder(this, NotificationChannelID)
      .setContentTitle(serverName)
      .setSmallIcon(R.drawable.ic_notification)
      .setColor(NotificationColor)
      .setVisibility(Notification.VISIBILITY_SECRET) // Don't display in lock screen
      .setContentIntent(mainActivityIntent)
      .setShowWhen(true)
      .setUsesChronometer(true)
  }

  override fun startTunnel(config: TunnelConfig) {
    return startTunnel(config, false)
  }

  @Synchronized
  private fun startTunnel(
    config: TunnelConfig,
    isAutoStart: Boolean,
  ) {
    Log.i("Starting tunnel ${config.id} for server ${config.name}")
    // We check if the VPN is already running. This happens when a user connects to a server while
    // already connected to another one.
    // Instead of tearing down the VPN and starting from scratch, we just replace the remote device
    // and restart the traffic exchange.
    val alreadyRunning = tunnelConfig != null && tunFd != null
    if (alreadyRunning) {
      // Broadcast the previous instance disconnect event before reassigning the tunnel config.
      _connectivityState.update { TunnelStatus.Disconnected }
      stopForeground(STOP_FOREGROUND_REMOVE)
      try {
        // Stops the remote device; does not tear down the VPN to avoid leaking traffic.
        stopRemoteDevice()
      } catch (e: java.lang.Exception) {
        Log.e("Failed to disconnect tunnel", e)
      }
    } else {
      // Make sure we have a fully clean state.
      tunnelConfig = null
      tunFd = null
    }

    val clientResult = Outline.newClient(config.transportConfig)
    if (clientResult.error != null) {
      Log.e(
        message = "Failed to create Outline Client",
        throwable = IllegalArgumentException(clientResult.error.message),
      )
      tearDownActiveTunnel()
      throw error("Failed to create Outline Client")
    }

    val remoteUdpForwardingEnabled: Boolean
    if (isAutoStart) {
      // TODO(fortuna): retest connectivity instead of restoring it. Needs a UDP test.
      remoteUdpForwardingEnabled = tunnelStore.isUdpSupported()
    } else {
      try {
        // Do not perform connectivity checks when connecting on startup. We should avoid failing
        // the connection due to a network error, as network may not be ready.
        val connResult: TCPAndUDPConnectivityResult = Outline.checkTCPAndUDPConnectivity(clientResult.client)
        Log.i("Go connectivity check result: $connResult")

        if (connResult.tcpError != null) {
          tearDownActiveTunnel()
          throw error(connResult.tcpError)
        }
        remoteUdpForwardingEnabled = connResult.getUDPError() == null
      } catch (_: Exception) {
        tearDownActiveTunnel()
        throw error("failed to check connectivity")
      }
    }
    tunnelConfig = config

    // If the VPN is already running, we skip the set up of the VPN routing.
    // TODO(fortuna): we should probably shutdown and restart, in case the config changed.
    if (!alreadyRunning) {
      // Only establish the VPN if this is not a tunnel restart.
      try {
        val dnsResolver = resources.getStringArray(R.array.dns_resolvers).random()
        val builder: Builder = Builder()
          .setSession(getApplicationName()) // Standard MTU.
          .setMtu(1500) // Some random local IP we believe won't conflict.
          .addAddress("10.111.222.1", 24)
          .addDnsServer(dnsResolver)
          .setBlocking(true)
          .addDisallowedApplication(packageName)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
          builder.setMetered(false)
        }
        // In absence of an API to remove routes, instead of adding the default route (0.0.0.0/0),
        // retrieve the list of subnets that excludes those reserved for special use.
        getReservedBypassSubnets().forEach { subnet ->
          builder.addRoute(subnet.address, subnet.prefix)
        }
        tunFd = builder.establish() ?: throw error("failed to establish the VPN")
        startNetworkConnectivityMonitor()
      } catch (e: Exception) {
        Log.e("Failed to establish the VPN", e)
        tearDownActiveTunnel()
        return
      }
    }

    // Start exchanging traffic between the local TUN device and the remote device.
    val result = Tun2socks.connectOutlineTunnel(tunFd?.fd?.toLong() ?: 0L, clientResult.client, remoteUdpForwardingEnabled)
    if (result.error != null) {
      tearDownActiveTunnel()
      throw error(result.error)
    }
    remoteDevice = result.tunnel

    startForegroundWithNotification(config.name)
    storeActiveTunnel(config, remoteUdpForwardingEnabled)
  }

  override fun stopTunnel(tunnelId: String) {
    if (!isTunnelActive(tunnelId)) throw error("VPN profile is not active")
    tearDownActiveTunnel()
  }

  override fun isTunnelActive(tunnelId: String): Boolean = tunnelConfig?.id == tunnelId

  private fun getReservedBypassSubnets(): List<Subnet> {
    val subnetStrings = resources.getStringArray(R.array.reserved_bypass_subnets)
    return subnetStrings.mapNotNull { runCatching { Subnet.parse(it) }.getOrNull() }
  }

  /** Returns the application name.  */
  private fun getApplicationName(): String {
    val packageManager = applicationContext.packageManager
    val appInfo = packageManager.getApplicationInfo(packageName, 0)
    return packageManager.getApplicationLabel(appInfo).toString()
  }

  private fun startNetworkConnectivityMonitor() {
    val connectivityManager = getSystemService(ConnectivityManager::class.java)
    val request = NetworkRequest.Builder()
      .addCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)
      .addCapability(NetworkCapabilities.NET_CAPABILITY_NOT_RESTRICTED)
      .build()
    // `registerNetworkCallback` returns the VPN interface as the default network since Android P.
    // Use `requestNetwork` instead (requires android.permission.CHANGE_NETWORK_STATE).
    if (Build.VERSION.SDK_INT < Build.VERSION_CODES.P) {
      connectivityManager.registerNetworkCallback(request, networkConnectivityMonitor)
    } else {
      connectivityManager.requestNetwork(request, networkConnectivityMonitor)
    }
  }

  private inner class NetworkConnectivityMonitor : NetworkCallback() {

    override fun onAvailable(network: Network) {
      val tunnelId = tunnelConfig?.id ?: ""
      val status = TunnelStatus.Connected(tunnelId)
      updateNotification(status)

      val isUdpSupported = remoteDevice?.updateUDPSupport() == true
      // Why do we need to persist this? TODO(fortuna): remove.
      tunnelStore.setIsUdpSupported(isUdpSupported)
    }

    override fun onLost(network: Network) {
      val tunnelId = tunnelConfig?.id ?: ""
      val status = TunnelStatus.Reconnecting(tunnelId)
      updateNotification(status)
    }
  }

  companion object {
    private const val NotificationServiceId: Int = 1
    private const val NotificationColor: Int = 0x00BFA5
    private const val NotificationChannelID: String = "outline-vpn"
  }

  private data class Subnet(var address: String, var prefix: Int) {

    companion object {
      fun parse(subnet: String): Subnet {
        val components: Array<String?> = subnet.split("/".toRegex(), limit = 2).toTypedArray()
        require(components.size == 2) { "Malformed subnet string" }
        return Subnet(components[0]!!, components[1]!!.toInt())
      }
    }
  }
}
