package com.bobarik.konspekt.core.network

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.annotation.RequiresApi
import com.bobarik.konspekt.core.network.models.TunnelStatus

class VpnServiceStarter : BroadcastReceiver() {

  @RequiresApi(Build.VERSION_CODES.O)
  override fun onReceive(context: Context, intent: Intent) {
    val tunnelStore = VpnTunnelStore(context)
    if (TunnelStatus.Connected == tunnelStore.getTunnelStatus()) return

    val serviceIntent = Intent(context, VpnTunnelServiceImpl::class.java)
    serviceIntent.putExtra(AutoStartExtra, true)
    context.startForegroundService(serviceIntent)
  }

  companion object {
    const val AutoStartExtra: String = "autostart"
  }
}
