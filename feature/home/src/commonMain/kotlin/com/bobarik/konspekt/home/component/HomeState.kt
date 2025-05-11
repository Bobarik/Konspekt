package com.bobarik.konspekt.home.component

import androidx.compose.runtime.Immutable
import app.cash.quiver.Absent
import app.cash.quiver.Outcome
import arrow.optics.optics
import com.bobarik.konspekt.home.model.NoteUi

@optics
@Immutable
data class HomeState(
  val searchQuery: String = "",
  val notes: Outcome<Nothing, List<NoteUi>> = Absent,
)
