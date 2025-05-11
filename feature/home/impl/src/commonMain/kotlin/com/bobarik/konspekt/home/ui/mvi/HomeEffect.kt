package com.bobarik.konspekt.home.ui.mvi

sealed interface HomeEffect {
  data object Back : HomeEffect
}
