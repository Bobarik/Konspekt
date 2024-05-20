package com.bobarik.korgy

import androidx.compose.runtime.Composable
import com.bobarik.korgy.root.component.RootComponent
import com.bobarik.korgy.root.ui.RootScreen
import com.bobarik.korgy.theme.AppTheme

@Composable
internal fun App(
    rootComponent: RootComponent,
) = AppTheme {
    RootScreen(rootComponent)
}
