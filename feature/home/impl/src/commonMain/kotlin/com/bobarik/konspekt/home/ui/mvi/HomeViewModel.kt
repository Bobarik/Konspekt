package com.bobarik.konspekt.home.ui.mvi

import androidx.lifecycle.viewModelScope
import app.cash.quiver.present
import com.bobarik.konspekt.arch.BaseViewModel
import com.bobarik.konspekt.arch.blockingReduce
import com.bobarik.konspekt.arch.reduce
import com.bobarik.konspekt.domain.models.Note
import com.bobarik.konspekt.domain.repository.NoteRepository
import kotlin.uuid.ExperimentalUuidApi
import kotlin.uuid.Uuid
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

@OptIn(FlowPreview::class, ExperimentalCoroutinesApi::class, ExperimentalUuidApi::class)
class HomeViewModel(
  private val notesRepository: NoteRepository,
) : BaseViewModel<HomeState, HomeEffect, HomeEvent>(
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
        reduce { copy(notes = list.present()) }
      }.launchIn(viewModelScope)
  }

  override fun onEvent(event: HomeEvent) = when (event) {
    is HomeEvent.OnQueryChanged -> onQueryChanged(event.query)
    is HomeEvent.OnButtonClicked -> onButtonClicked()
  }

  private fun onButtonClicked() {
    intent {
      notesRepository.upsertNote(Note(title = "Hello", note = Uuid.random().toString()))
    }
  }

  private fun onQueryChanged(query: String) = blockingReduce {
    currentSearchQuery.value = query
    copy(searchQuery = query)
  }
}
