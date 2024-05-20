package com.bobarik.korgy.login.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import com.bobarik.korgy.arch.collectState
import com.bobarik.korgy.login.component.LoginComponent

@Composable
fun LoginScreen(
    loginComponent: LoginComponent
) {

    val state by loginComponent.collectState()

    LoginScreenContent(
        stateProvider = { state },
        onEvent = loginComponent::onEvent
    )
}
