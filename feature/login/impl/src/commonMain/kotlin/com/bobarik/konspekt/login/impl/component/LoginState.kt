package com.bobarik.konspekt.login.impl.component

import com.bobarik.konspekt.arch.BaseState

data class LoginState(
    val login: String = "",
    val password: String = "",
    val isPasswordVisible: Boolean = false,
) : BaseState
