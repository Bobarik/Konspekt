package com.bobarik.korgy.home.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import com.bobarik.korgy.arch.collectState
import com.bobarik.korgy.home.component.HomeComponent

@Composable
fun HomeScreen(
    homeComponent: HomeComponent
) {
    val state by homeComponent.collectState()//TODO

    HomeScreenContent()
}


