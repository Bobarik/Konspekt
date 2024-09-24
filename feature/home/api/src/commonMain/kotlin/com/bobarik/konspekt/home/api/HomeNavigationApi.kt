package com.bobarik.konspekt.home.api

import androidx.navigation.NavGraphBuilder
import com.bobarik.konspekt.navigation.Screen
import kotlinx.serialization.Serializable

interface HomeNavigationApi {

    fun homeScreen(): Screen

    fun homeGraph(builder: NavGraphBuilder)
}

@Serializable
data object HomeScreen : Screen