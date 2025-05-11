package com.bobarik.konspekt.login.screen.mvi

import com.bobarik.konspekt.arch.BaseStore
import com.bobarik.konspekt.arch.blockingReduce
import com.bobarik.konspekt.arch.postEffect

class LoginStore : BaseStore<LoginState, LoginEffect, LoginEvent>(
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

  private fun onLoginChanged(login: String) = blockingReduce { copy(login = login) }

  private fun onPasswordChanged(password: String) = blockingReduce { copy(password = password) }

  private fun onPasswordVisibilityChanged() = blockingReduce { copy(isPasswordVisible = !isPasswordVisible) }

  private fun onHomeNavigateClick() = postEffect(LoginEffect.NavigateHome)

}
