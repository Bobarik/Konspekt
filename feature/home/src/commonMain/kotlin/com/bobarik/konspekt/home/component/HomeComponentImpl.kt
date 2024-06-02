package com.bobarik.konspekt.home.component

import arrow.optics.copy
import com.arkivanov.decompose.ComponentContext
import com.bobarik.konspekt.arch.ContainerComponent
import com.bobarik.konspekt.arch.blockingReduce
import com.bobarik.konspekt.arch.reduce
import com.bobarik.konspekt.domain.models.Note
import com.bobarik.konspekt.domain.repository.NoteRepository
import com.bobarik.konspekt.home.model.toUi
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import org.orbitmvi.orbit.syntax.simple.intent
import java.util.UUID

@OptIn(FlowPreview::class, ExperimentalCoroutinesApi::class)
class HomeComponentImpl(
    componentContext: ComponentContext,
    private val notesRepository: NoteRepository,
    private val onBackClicked: () -> Unit
) : ContainerComponent<HomeState, Nothing, HomeEvent>(
    initState = HomeState(),
    componentContext = componentContext
), HomeComponent {

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
            }.launchIn(coroutineScope)
    }

    override fun onEvent(event: HomeEvent) = when (event) {
        is HomeEvent.OnQueryChanged -> onQueryChanged(event.query)
        is HomeEvent.OnButtonClicked -> onButtonClicked()
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

    private fun onNavigateBack() = onBackClicked()
}
