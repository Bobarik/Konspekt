package com.bobarik.konspekt.login.component

data class LoginState(
  val login: String = "",
  val password: String = "",
  val isPasswordVisible: Boolean = false,
)
