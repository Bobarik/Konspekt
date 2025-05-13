package com.bobarik.konspekt.core.network

import android.content.Context
import android.content.SharedPreferences
import androidx.core.content.edit
import com.bobarik.konspekt.core.network.models.TunnelConfig
import com.bobarik.konspekt.core.network.models.TunnelStatus
import kotlinx.serialization.json.Json

class VpnTunnelStore(context: Context) {

  private val preferences: SharedPreferences? = context.getSharedPreferences(VpnTunnelStore::class.java.name, Context.MODE_PRIVATE)

  fun save(tunnel: TunnelConfig) = preferences?.edit {
    putString(TunnelKey, Json.encodeToString(tunnel))
  }

  fun load(): TunnelConfig? = preferences?.getString(TunnelKey, null)?.let { Json.decodeFromString<TunnelConfig>(it) }

  fun setTunnelStatus(status: TunnelStatus) = preferences?.edit(commit = true) {
    putString(TunnelStatusKey, status.name)
  }

  fun getTunnelStatus(): TunnelStatus {
    val tunnelStatus = preferences?.getString(TunnelStatusKey, null) ?: return TunnelStatus.Disconnected
    return TunnelStatus.valueOf(tunnelStatus)
  }

  fun setIsUdpSupported(isUdpSupported: Boolean) = preferences?.edit(commit = true) {
    putBoolean(TunnelSupportsUdp, isUdpSupported)
  }

  fun isUdpSupported(): Boolean = preferences?.getBoolean(TunnelSupportsUdp, false) == true

  companion object {
    const val TunnelKey: String = "connection"
    const val TunnelStatusKey: String = "connectionStatus"
    const val TunnelSupportsUdp: String = "connectionSupportsUdp"
  }
}
