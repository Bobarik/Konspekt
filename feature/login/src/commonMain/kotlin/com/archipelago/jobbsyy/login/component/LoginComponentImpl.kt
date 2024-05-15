package com.archipelago.jobbsyy.login.component

import com.archipelago.jobbsyy.arch.ContainerComponent
import com.archipelago.jobbsyy.arch.blockingReduce
import com.arkivanov.decompose.ComponentContext

class LoginComponentImpl(
    componentContext: ComponentContext,
    private val onHomeNavigate: () -> Unit
) : ContainerComponent<LoginState, Nothing, LoginEvent>(
    initState = LoginState(),
    componentContext = componentContext
), LoginComponent {

    override fun onEvent(event: LoginEvent) {
        when (event) {
            LoginEvent.OnHomeNavigateClick -> onHomeNavigateClick()
            is LoginEvent.OnLoginChanged -> onLoginChanged(event.login)
            is LoginEvent.OnPasswordChanged -> onPasswordChanged(event.password)
            LoginEvent.OnPasswordVisibilityChanged -> onPasswordVisibilityChanged()
        }
    }

    private fun onLoginChanged(login: String) = blockingReduce { state.copy(login = login) }

    private fun onPasswordChanged(password: String) = blockingReduce {
        state.copy(password = password)
    }

    private fun onPasswordVisibilityChanged() = blockingReduce {
        state.copy(isPasswordVisible = !state.isPasswordVisible)
    }

    private fun onHomeNavigateClick() {
        onHomeNavigate()
    }
}
