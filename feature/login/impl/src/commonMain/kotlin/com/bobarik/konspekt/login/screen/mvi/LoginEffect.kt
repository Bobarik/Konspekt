package com.bobarik.konspekt.login.screen.mvi

sealed interface LoginEffect {
  data object NavigateHome : LoginEffect
}
