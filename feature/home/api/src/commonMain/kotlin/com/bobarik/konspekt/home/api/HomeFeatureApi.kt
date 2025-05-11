package com.bobarik.konspekt.home.api

import androidx.navigation.NavGraphBuilder
import com.bobarik.konspekt.navigation.Screen
import kotlinx.serialization.Serializable

interface HomeFeatureApi {

  context(NavGraphBuilder)
  fun homeGraph()
}

@Serializable
data object HomeScreen : Screen
