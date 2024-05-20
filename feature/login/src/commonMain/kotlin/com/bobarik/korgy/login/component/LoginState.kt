package com.bobarik.korgy.login.component

import com.bobarik.korgy.arch.BaseState

data class LoginState(
    val login: String = "",
    val password: String = "",
    val isPasswordVisible: Boolean = false,
) : BaseState
