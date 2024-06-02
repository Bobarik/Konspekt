package com.bobarik.konspekt.home.ui

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.bobarik.konspekt.home.component.HomeEvent
import com.bobarik.konspekt.home.component.HomeState
import com.bobarik.konspekt.home.ui.composables.HomeNoteItem
import kotlin.math.roundToInt

@Composable
internal fun HomeScreenContent(
    state: HomeState,
    onEvent: (HomeEvent) -> Unit
) = Scaffold(
    floatingActionButton = {
        FloatingActionButton(
            onClick = { onEvent(HomeEvent.OnButtonClicked) },
        ) {
            Icon(imageVector = Icons.Default.Add, contentDescription = null)
        }
    }
) { insetPadding ->
    Column(
        modifier = Modifier
            .padding(insetPadding)
            .fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(12.dp),
    ) {
        AnimatedContent(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f),
            targetState = state.generalState,
            contentKey = { it::class }
        ) { generalState ->
            when (generalState) {
                is HomeState.ScreenGeneralState.Content -> HomeScreenNotesContent(
                    state.searchQuery,
                    generalState,
                    onEvent
                )
                is HomeState.ScreenGeneralState.Empty -> HomeScreenEmptyStub()
                HomeState.ScreenGeneralState.Loading -> HomeScreenLoadingContent()
            }
        }
    }
}

@Composable
internal fun ColumnScope.HomeScreenEmptyStub() {
    Text(text = "Create your first note!")
}

@Composable
internal fun ColumnScope.HomeScreenLoadingContent() = Box(
    modifier = Modifier
        .fillMaxWidth()
        .weight(1f),
    contentAlignment = Alignment.Center
) {
    val infiniteTransition = rememberInfiniteTransition()
    val numberOfDots by infiniteTransition.animateFloat(
        initialValue = 0.5f,
        targetValue = 3.5f,
        animationSpec = infiniteRepeatable(
            animation = tween(3000, easing = LinearEasing),
            repeatMode = RepeatMode.Restart
        )
    )
    val dots by remember { derivedStateOf { ".".repeat(numberOfDots.roundToInt()) } }

    Text(text = "Loading$dots")
}

@Composable
internal fun ColumnScope.HomeScreenNotesContent(
    query: String,
    generalState: HomeState.ScreenGeneralState.Content,
    onEvent: (HomeEvent) -> Unit
) = LazyVerticalGrid(
    modifier = Modifier
        .fillMaxWidth()
        .weight(1f),
    columns = GridCells.Fixed(2),
    verticalArrangement = Arrangement.spacedBy(12.dp),
    horizontalArrangement = Arrangement.spacedBy(12.dp),
    contentPadding = PaddingValues(12.dp)
) {
    item(
        span = { GridItemSpan(this.maxCurrentLineSpan) }
    ) {
        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = query,
            onValueChange = { text -> onEvent(HomeEvent.OnQueryChanged(text)) }
        )
    }
    items(
        items = generalState.notes,
        key = { it.id }
    ) { note ->
        HomeNoteItem(note = note)
    }
}
