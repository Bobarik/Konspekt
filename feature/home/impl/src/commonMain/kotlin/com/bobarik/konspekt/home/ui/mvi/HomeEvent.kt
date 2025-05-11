package com.bobarik.konspekt.home.ui.mvi

sealed interface HomeEvent {

  data class OnQueryChanged(val query: String) : HomeEvent
  data object OnButtonClicked : HomeEvent
}
