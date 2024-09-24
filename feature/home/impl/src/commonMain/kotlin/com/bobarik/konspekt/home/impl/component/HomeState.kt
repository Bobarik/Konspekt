package com.bobarik.konspekt.home.impl.component

import arrow.optics.optics
import com.bobarik.konspekt.arch.BaseState
import com.bobarik.konspekt.home.impl.model.NoteUi

@optics
data class HomeState(
    val searchQuery: String = "",
    val generalState: ScreenGeneralState = ScreenGeneralState.Loading
) : BaseState {

    companion object;

    sealed interface ScreenGeneralState {

        data class Content(val notes: List<NoteUi>) : ScreenGeneralState {
            companion object
        }

        data object Loading : ScreenGeneralState

        data object Empty : ScreenGeneralState
    }
}
