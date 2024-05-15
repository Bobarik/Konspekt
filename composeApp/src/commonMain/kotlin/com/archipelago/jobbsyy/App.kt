package com.archipelago.jobbsyy

import androidx.compose.runtime.Composable
import com.archipelago.jobbsyy.root.component.RootComponent
import com.archipelago.jobbsyy.root.ui.RootScreen
import com.archipelago.jobbsyy.theme.AppTheme

@Composable
internal fun App(
    rootComponent: RootComponent,
) = AppTheme {
    RootScreen(rootComponent)
}
