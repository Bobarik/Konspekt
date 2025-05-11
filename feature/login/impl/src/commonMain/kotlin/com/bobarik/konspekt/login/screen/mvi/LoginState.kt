package com.bobarik.konspekt.login.screen.mvi

data class LoginState(
  val login: String = "",
  val password: String = "",
  val isPasswordVisible: Boolean = false,
)
