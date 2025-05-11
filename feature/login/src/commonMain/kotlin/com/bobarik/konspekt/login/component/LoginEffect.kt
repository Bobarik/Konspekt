package com.bobarik.konspekt.login.component

sealed interface LoginEffect {
  data object NavigateHome : LoginEffect
}
