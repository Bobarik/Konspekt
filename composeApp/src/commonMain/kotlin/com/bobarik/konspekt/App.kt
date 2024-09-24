package com.bobarik.konspekt

import androidx.compose.runtime.Composable
import androidx.navigation.compose.rememberNavController
import com.bobarik.konspekt.root.ui.RootGraph
import com.bobarik.konspekt.theme.AppTheme
import org.koin.compose.KoinContext

@Composable
internal fun App() = KoinContext {
    AppTheme {
        RootGraph(controller = rememberNavController())
    }
}
