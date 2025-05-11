package com.bobarik.konspekt.home.ui

import app.cash.quiver.present
import com.bobarik.konspekt.arch.BaseStore
import com.bobarik.konspekt.arch.blockingReduce
import com.bobarik.konspekt.arch.reduce
import com.bobarik.konspekt.domain.models.Note
import com.bobarik.konspekt.domain.repository.NoteRepository
import com.bobarik.konspekt.home.component.HomeEffect
import com.bobarik.konspekt.home.component.HomeEvent
import com.bobarik.konspekt.home.component.HomeState
import com.bobarik.konspekt.home.model.toUi
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import java.util.UUID

@OptIn(FlowPreview::class, ExperimentalCoroutinesApi::class)
class HomeStore(
  private val notesRepository: NoteRepository,
) : BaseStore<HomeState, HomeEffect, HomeEvent>(
  initState = HomeState(),
) {

  private val currentSearchQuery = MutableStateFlow("")

  init {
    currentSearchQuery
      .debounce(300L)
      .flatMapLatest { query ->
        when {
          query.isEmpty() -> notesRepository.getAllNotes()
          else -> notesRepository.searchNotes(query)
        }
      }.onEach { list ->
        reduce { state.copy(notes = list.map(Note::toUi).present()) }
      }.launchIn(coroutineScope)
  }

  override fun onEvent(event: HomeEvent) = when (event) {
    is HomeEvent.OnQueryChanged -> onQueryChanged(event.query)
    is HomeEvent.OnButtonClicked -> onButtonClicked()
  }

  private fun onButtonClicked() {
    intent {
      notesRepository.upsertNote(Note(title = "Hello", note = UUID.randomUUID().toString()))
    }
  }

  private fun onQueryChanged(query: String) = blockingReduce {
    currentSearchQuery.value = query
    state.copy(searchQuery = query)
  }
}
