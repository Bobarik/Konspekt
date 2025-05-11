package com.bobarik.konspekt.home.navigation

import androidx.navigation.NavGraphBuilder
import com.bobarik.konspekt.home.api.HomeFeatureApi
import com.bobarik.konspekt.home.ui.homeScreen

class HomeFeatureApiImpl : HomeFeatureApi {

  context(NavGraphBuilder)
  override fun homeGraph() = homeScreen()
}
