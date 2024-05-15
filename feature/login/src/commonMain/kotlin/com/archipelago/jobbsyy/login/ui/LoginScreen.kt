package com.archipelago.jobbsyy.login.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import com.archipelago.jobbsyy.arch.collectState
import com.archipelago.jobbsyy.login.component.LoginComponent

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
