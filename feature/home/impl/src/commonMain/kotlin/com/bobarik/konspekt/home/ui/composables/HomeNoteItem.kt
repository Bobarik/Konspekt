package com.bobarik.konspekt.home.ui.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.LazyGridItemScope
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.bobarik.konspekt.domain.models.Note

@Composable
fun LazyGridItemScope.HomeNoteItem(
  modifier: Modifier = Modifier,
  note: Note,
) = Column(
  modifier = modifier.fillMaxWidth()
    .animateItem(fadeInSpec = null, fadeOutSpec = null)
    .clip(MaterialTheme.shapes.large)
    .background(MaterialTheme.colorScheme.primary)
    .padding(12.dp),
  verticalArrangement = Arrangement.spacedBy(16.dp),
) {
  Text(text = note.title)
  Text(text = note.note)
}
