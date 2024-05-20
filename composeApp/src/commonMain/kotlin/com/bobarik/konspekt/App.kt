package com.bobarik.konspekt

import androidx.compose.runtime.Composable
import com.bobarik.konspekt.root.component.RootComponent
import com.bobarik.konspekt.root.ui.RootScreen
import com.bobarik.konspekt.theme.AppTheme

@Composable
internal fun App(
    rootComponent: RootComponent,
) = AppTheme {
    RootScreen(rootComponent)
}
