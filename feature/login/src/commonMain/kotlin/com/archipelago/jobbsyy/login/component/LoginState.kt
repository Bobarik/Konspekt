package com.archipelago.jobbsyy.login.component

import com.archipelago.jobbsyy.arch.BaseState

data class LoginState(
    val login: String = "",
    val password: String = "",
    val isPasswordVisible: Boolean = false,
) : BaseState
