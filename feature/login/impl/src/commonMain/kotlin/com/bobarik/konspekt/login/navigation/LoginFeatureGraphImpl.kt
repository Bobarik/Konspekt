package com.bobarik.konspekt.login.navigation

import androidx.navigation.NavGraphBuilder
import com.bobarik.konspekt.login.screen.ui.loginScreen
import com.bobarik.konspekt.navigation.FeatureGraph

class LoginFeatureGraphImpl : FeatureGraph {

  context(NavGraphBuilder)
  override fun featureGraph() = loginScreen()
}
