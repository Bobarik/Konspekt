package com.bobarik.konspekt.home.component

import com.bobarik.konspekt.arch.BaseEvent

sealed interface HomeEvent : BaseEvent {

    data class OnQueryChanged(val query: String) : HomeEvent
    data object OnButtonClicked : HomeEvent
}
