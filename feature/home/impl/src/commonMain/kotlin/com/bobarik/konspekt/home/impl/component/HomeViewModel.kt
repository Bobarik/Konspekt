package com.bobarik.konspekt.home.impl.component

import androidx.lifecycle.viewModelScope
import arrow.optics.copy
import com.bobarik.konspekt.arch.MviViewModel
import com.bobarik.konspekt.arch.blockingReduce
import com.bobarik.konspekt.arch.reduce
import com.bobarik.konspekt.domain.models.Note
import com.bobarik.konspekt.domain.repository.NoteRepository
import com.bobarik.konspekt.home.impl.component.HomeEvent.OnButtonClicked
import com.bobarik.konspekt.home.impl.component.HomeEvent.OnQueryChanged
import com.bobarik.konspekt.home.impl.model.toUi
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import java.util.UUID

@OptIn(FlowPreview::class, ExperimentalCoroutinesApi::class)
class HomeViewModel(
    private val notesRepository: NoteRepository,
) : MviViewModel<HomeState, HomeEffect, HomeEvent>(
    initState = HomeState(),
) {

    private val currentSearchQuery = MutableStateFlow("")

    init {
        currentSearchQuery
            .debounce(300L)
            .flatMapLatest { query ->
                reduce {
                    state.copy {

                    }
                }
                if (query.isEmpty()) {
                    notesRepository.getAllNotes()
                } else {
                    notesRepository.searchNotes(query)
                }
            }
            .onEach { list ->
                reduce {
                    state.copy {
                        HomeState.generalState set HomeState.ScreenGeneralState.Content(
                            notes = list.map(Note::toUi)
                        )
                    }
                }
            }.launchIn(viewModelScope)
    }

    override fun onEvent(event: HomeEvent) = when (event) {
        is OnQueryChanged -> onQueryChanged(event.query)
        is OnButtonClicked -> onButtonClicked()
    }

    private fun onButtonClicked() {
        intent {
            notesRepository.upsertNote(
                Note(
                    title = "Hello",
                    note = UUID.randomUUID().toString()
                )
            )
        }
    }

    private fun onQueryChanged(query: String) = blockingReduce {
        currentSearchQuery.value = query
        state.copy(searchQuery = query)
    }
}
