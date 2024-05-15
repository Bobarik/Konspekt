package com.archipelago.jobbsyy.home.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import com.archipelago.jobbsyy.arch.collectState
import com.archipelago.jobbsyy.home.component.HomeComponent

@Composable
fun HomeScreen(
    homeComponent: HomeComponent
) {
    val state by homeComponent.collectState()//TODO

    HomeScreenContent()
}


