package com.bobarik.konspekt.navigation

import androidx.navigation.NavGraphBuilder

fun interface FeatureGraph {

  context(NavGraphBuilder)
  fun featureGraph()
}
