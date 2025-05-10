package com.bobarik.konspekt.login.component

sealed interface LoginEvent {
    data class OnLoginChanged(val login: String) : LoginEvent
    data class OnPasswordChanged(val password: String) : LoginEvent
    data object OnPasswordVisibilityChanged : LoginEvent
    data object OnHomeNavigateClick : LoginEvent
}
