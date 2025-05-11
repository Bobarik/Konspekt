package com.bobarik.konspekt.login.api

import androidx.navigation.NavGraphBuilder
import com.bobarik.konspekt.navigation.Screen
import kotlinx.serialization.Serializable

interface LoginFeatureApi {

  context(NavGraphBuilder)
  fun loginGraph()
}

@Serializable
data object LoginScreen : Screen
