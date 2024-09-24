package com.bobarik.konspekt.login.impl.component

import com.bobarik.konspekt.arch.MviViewModel
import com.bobarik.konspekt.arch.blockingReduce
import com.bobarik.konspekt.arch.postEffect

class LoginViewModel : MviViewModel<LoginState, LoginEffect, LoginEvent>(
    initState = LoginState(),
) {

    override fun onEvent(event: LoginEvent) {
        when (event) {
            is LoginEvent.OnHomeNavigateClick -> onHomeNavigateClick()
            is LoginEvent.OnLoginChanged -> onLoginChanged(event.login)
            is LoginEvent.OnPasswordChanged -> onPasswordChanged(event.password)
            is LoginEvent.OnPasswordVisibilityChanged -> onPasswordVisibilityChanged()
        }
    }

    private fun onLoginChanged(login: String) = blockingReduce { state.copy(login = login) }

    private fun onPasswordChanged(password: String) = blockingReduce {
        state.copy(password = password)
    }

    private fun onPasswordVisibilityChanged() = blockingReduce {
        state.copy(isPasswordVisible = !state.isPasswordVisible)
    }

    private fun onHomeNavigateClick() = postEffect(LoginEffect.NavigateToHome)
}
