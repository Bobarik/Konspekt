package com.bobarik.konspekt.home.impl.model

import com.bobarik.konspekt.domain.models.Note

//TODO: Find out why optics doesn't work between modules
data class NoteUi(
    val id: Long,
    val title: String,
    val note: String
)

fun NoteUi.toDomain() = Note(id = id, title = title, note = note)
fun Note.toUi() = NoteUi(id = id, title = title, note = note)
