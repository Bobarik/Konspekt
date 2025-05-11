package com.bobarik.konspekt.login.screen.ui

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.bobarik.konspekt.arch.collectAsState
import com.bobarik.konspekt.arch.collectSideEffect
import com.bobarik.konspekt.home.api.HomeScreen
import com.bobarik.konspekt.login.api.LoginScreen
import com.bobarik.konspekt.login.screen.mvi.LoginEffect
import com.bobarik.konspekt.login.screen.mvi.LoginViewModel
import com.bobarik.konspekt.navigation.LocalNavController
import org.koin.compose.viewmodel.koinViewModel

fun NavGraphBuilder.loginScreen() {
  composable<LoginScreen> {
    val viewModel = koinViewModel<LoginViewModel>()
    val state = viewModel.collectAsState()
    val navController = LocalNavController.current

    LoginScreenContent(
      stateProvider = { state.value },
      onEvent = viewModel::onEvent,
    )

    viewModel.collectSideEffect { effect ->
      when (effect) {
        LoginEffect.NavigateHome -> navController.navigate(HomeScreen)
      }
    }
  }
}
