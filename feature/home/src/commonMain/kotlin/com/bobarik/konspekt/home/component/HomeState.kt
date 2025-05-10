package com.bobarik.konspekt.home.component

import arrow.optics.optics
import com.bobarik.konspekt.home.model.NoteUi

@optics
data class HomeState(
    val searchQuery: String = "",
    val generalState: ScreenGeneralState = ScreenGeneralState.Loading
) {

    companion object;

    sealed interface ScreenGeneralState {

        data class Content(val notes: List<NoteUi>) : ScreenGeneralState {
            companion object
        }

        data object Loading : ScreenGeneralState

        data object Empty : ScreenGeneralState
    }
}
