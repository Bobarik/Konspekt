package com.bobarik.konspekt.core.network.models

import androidx.annotation.StringRes
import com.bobarik.konspekt.core.network.R

enum class TunnelStatus(val value: Int, @StringRes val labelId: Int) {
  Invalid(-1, R.string.notification_state_reconnecting),
  Connected(0, R.string.notification_state_connected),
  Disconnected(1, R.string.notification_state_reconnecting),
  Reconnecting(2, R.string.notification_state_reconnecting);
}
