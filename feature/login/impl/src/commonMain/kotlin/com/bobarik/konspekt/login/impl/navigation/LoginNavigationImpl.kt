package com.bobarik.konspekt.login.impl.navigation

import androidx.navigation.NavGraphBuilder
import com.bobarik.konspekt.home.api.LoginNavigationApi
import com.bobarik.konspekt.home.api.LoginScreen
import com.bobarik.konspekt.login.impl.ui.LoginScreen
import com.bobarik.konspekt.navigation.Screen

class LoginNavigationImpl : LoginNavigationApi {

    override fun loginScreen(): Screen = LoginScreen

    override fun loginGraph(builder: NavGraphBuilder) = builder.LoginScreen()
}
