package com.bobarik.konspekt.home.impl.navigation

import androidx.navigation.NavGraphBuilder
import com.bobarik.konspekt.home.api.HomeNavigationApi
import com.bobarik.konspekt.home.api.HomeScreen
import com.bobarik.konspekt.home.impl.ui.HomeScreen
import com.bobarik.konspekt.navigation.Screen

class HomeNavigationImpl : HomeNavigationApi {

    override fun homeScreen(): Screen = HomeScreen

    override fun homeGraph(builder: NavGraphBuilder) = builder.HomeScreen()
}
