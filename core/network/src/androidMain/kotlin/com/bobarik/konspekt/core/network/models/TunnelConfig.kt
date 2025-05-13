package com.bobarik.konspekt.core.network.models

import kotlinx.serialization.Serializable

@Serializable
data class TunnelConfig(
  val id: String,
  val name: String,
  val transportConfig: String,
)
