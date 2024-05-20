package com.bobarik.konspekt.login.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import com.bobarik.konspekt.arch.collectState
import com.bobarik.konspekt.login.component.LoginComponent

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
