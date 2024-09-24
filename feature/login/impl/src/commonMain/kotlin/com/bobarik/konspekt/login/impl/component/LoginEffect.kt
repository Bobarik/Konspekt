package com.bobarik.konspekt.login.impl.component

import com.bobarik.konspekt.arch.BaseSideEffect

sealed interface LoginEffect : BaseSideEffect {

    data object NavigateToHome: LoginEffect
}