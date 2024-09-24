package com.bobarik.konspekt.root.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.bobarik.konspekt.home.api.HomeNavigationApi
import com.bobarik.konspekt.home.api.LoginNavigationApi
import com.bobarik.konspekt.home.api.LoginScreen
import com.bobarik.konspekt.navigation.LocalNavController
import org.koin.compose.koinInject

@Composable
fun RootGraph(
    controller: NavHostController = rememberNavController(),
) {
    val homeNavigation = koinInject<HomeNavigationApi>()
    val loginNavigation = koinInject<LoginNavigationApi>()

    CompositionLocalProvider(LocalNavController provides controller) {
        NavHost(
            navController = controller,
            startDestination = LoginScreen
        ) {
            homeNavigation.homeGraph(this)
            loginNavigation.loginGraph(this)
        }
    }
}
