package com.bobarik.konspekt.login.component

import com.bobarik.konspekt.arch.BaseState

data class LoginState(
    val login: String = "",
    val password: String = "",
    val isPasswordVisible: Boolean = false,
) : BaseState
