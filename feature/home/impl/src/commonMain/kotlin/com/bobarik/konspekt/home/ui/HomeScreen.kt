package com.bobarik.konspekt.home.ui

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.bobarik.konspekt.arch.collectAsState
import com.bobarik.konspekt.arch.collectSideEffect
import com.bobarik.konspekt.home.api.HomeScreen
import com.bobarik.konspekt.home.ui.mvi.HomeEffect
import com.bobarik.konspekt.home.ui.mvi.HomeViewModel
import com.bobarik.konspekt.navigation.LocalNavController
import org.koin.compose.viewmodel.koinViewModel

fun NavGraphBuilder.homeScreen() {
  composable<HomeScreen> {
    val store = koinViewModel<HomeViewModel>()
    val state = store.collectAsState()
    val navigator = LocalNavController.current

    HomeScreenContent(state, store::onEvent)

    store.collectSideEffect { effect ->
      when (effect) {
        is HomeEffect.Back -> navigator.popBackStack()
      }
    }
  }
}
