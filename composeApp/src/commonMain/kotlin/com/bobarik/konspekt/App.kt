package com.bobarik.konspekt

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.remember
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.bobarik.konspekt.login.api.LoginScreen
import com.bobarik.konspekt.navigation.FeatureGraph
import com.bobarik.konspekt.navigation.LocalNavController
import com.bobarik.konspekt.theme.AppTheme
import org.koin.compose.KoinContext
import org.koin.compose.currentKoinScope

@Composable
internal fun App() = KoinContext {
  AppTheme {
    val koinScope = currentKoinScope()
    val controller = rememberNavController()
    val featureGraphs: List<FeatureGraph> = remember(koinScope) { koinScope.getAll() }

    CompositionLocalProvider(LocalNavController provides controller) {
      NavHost(
        navController = controller,
        startDestination = LoginScreen,
      ) {
        featureGraphs.forEach { feature ->
          feature.featureGraph()
        }
      }
    }
  }
}
