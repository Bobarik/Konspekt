package com.bobarik.konspekt.home.impl.ui

import androidx.compose.runtime.getValue
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.bobarik.konspekt.arch.collectState
import com.bobarik.konspekt.home.api.HomeScreen
import com.bobarik.konspekt.home.impl.component.HomeViewModel
import org.koin.compose.viewmodel.koinViewModel

fun NavGraphBuilder.HomeScreen() = composable<HomeScreen> {
    val viewModel = koinViewModel<HomeViewModel>()
    val state by viewModel.collectState()

    HomeScreenContent(state, viewModel::onEvent)
}
