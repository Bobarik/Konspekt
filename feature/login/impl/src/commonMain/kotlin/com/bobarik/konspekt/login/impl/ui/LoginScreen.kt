package com.bobarik.konspekt.login.impl.ui

import androidx.compose.runtime.getValue
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.bobarik.konspekt.arch.collectSideEffect
import com.bobarik.konspekt.arch.collectState
import com.bobarik.konspekt.home.api.HomeScreen
import com.bobarik.konspekt.home.api.LoginScreen
import com.bobarik.konspekt.login.impl.component.LoginEffect
import com.bobarik.konspekt.login.impl.component.LoginViewModel
import com.bobarik.konspekt.navigation.LocalNavController
import org.koin.compose.viewmodel.koinViewModel

fun NavGraphBuilder.LoginScreen() = composable<LoginScreen> {
    val viewModel = koinViewModel<LoginViewModel>()
    val state by viewModel.collectState()
    val navController = LocalNavController.current

    LoginScreenContent(
        state = state,
        onEvent = viewModel::onEvent
    )

    viewModel.collectSideEffect { effect ->
        handleEffect(navController = navController, effect = effect)
    }
}

private fun handleEffect(
    navController: NavController,
    effect: LoginEffect
) = when (effect) {
    LoginEffect.NavigateToHome -> navController.navigate(HomeScreen)
}