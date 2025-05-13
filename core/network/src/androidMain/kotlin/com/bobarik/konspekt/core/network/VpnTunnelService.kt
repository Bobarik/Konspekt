package com.bobarik.konspekt.core.network

import com.bobarik.konspekt.core.network.models.TunnelConfig

interface VpnTunnelService {
  fun startTunnel(config: TunnelConfig)
  fun stopTunnel(tunnelId: String)
  fun isTunnelActive(tunnelId: String): Boolean
}
