package com.bobarik.konspekt.login.navigation

import androidx.navigation.NavGraphBuilder
import com.bobarik.konspekt.login.api.LoginFeatureApi
import com.bobarik.konspekt.login.screen.ui.loginScreen

class LoginFeatureApiImpl : LoginFeatureApi {

  context(NavGraphBuilder)
  override fun loginGraph() = loginScreen()
}
