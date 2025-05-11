package com.bobarik.konspekt.home.ui.mvi

import app.cash.quiver.Absent
import app.cash.quiver.Outcome
import com.bobarik.konspekt.domain.models.Note

data class HomeState(
  val searchQuery: String = "",
  val notes: Outcome<Nothing, List<Note>> = Absent,
)
