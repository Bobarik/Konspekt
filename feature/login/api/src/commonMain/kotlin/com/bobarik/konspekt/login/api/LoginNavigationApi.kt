package com.bobarik.konspekt.home.api

import androidx.navigation.NavGraphBuilder
import com.bobarik.konspekt.navigation.Screen
import kotlinx.serialization.Serializable

interface LoginNavigationApi {

    fun loginScreen(): Screen

    fun loginGraph(builder: NavGraphBuilder)
}

@Serializable
data object LoginScreen : Screen