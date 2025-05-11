package com.bobarik.konspekt.home.component

sealed interface HomeEffect {
  data object Back : HomeEffect
}
