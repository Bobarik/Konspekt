package com.bobarik.konspekt.home.navigation

import androidx.navigation.NavGraphBuilder
import com.bobarik.konspekt.home.ui.homeScreen
import com.bobarik.konspekt.navigation.FeatureGraph

class HomeFeatureGraphImpl : FeatureGraph {

  context(NavGraphBuilder)
  override fun featureGraph() = homeScreen()
}
