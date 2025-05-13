package com.bobarik.konspekt.core.network.models

import androidx.annotation.StringRes
import com.bobarik.konspekt.core.network.R
import kotlinx.serialization.Serializable

@Serializable
sealed class TunnelStatus(val value: Int, @StringRes val labelId: Int) {

  @Serializable
  data object Invalid : TunnelStatus(-1, R.string.notification_state_reconnecting)

  @Serializable
  data class Connected(val id: String) : TunnelStatus(0, R.string.notification_state_connected)

  @Serializable
  data object Disconnected : TunnelStatus(1, R.string.notification_state_reconnecting)

  @Serializable
  data class Reconnecting(val id: String) : TunnelStatus(2, R.string.notification_state_reconnecting)
}
