package com.bobarik.konspekt.home.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import com.bobarik.konspekt.arch.collectState
import com.bobarik.konspekt.home.component.HomeComponent

@Composable
fun HomeScreen(
    homeComponent: HomeComponent
) {
    val state by homeComponent.collectState()

    HomeScreenContent(state, homeComponent::onEvent)
}


