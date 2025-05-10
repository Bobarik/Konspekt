package com.bobarik.konspekt.home.component

sealed interface HomeEvent {

  data class OnQueryChanged(val query: String) : HomeEvent
  data object OnButtonClicked : HomeEvent
}
