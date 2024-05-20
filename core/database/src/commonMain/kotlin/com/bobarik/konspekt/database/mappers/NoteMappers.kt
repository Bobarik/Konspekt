package com.bobarik.konspekt.database.mappers

import com.bobarik.konspekt.database.entities.NoteEntity
import com.bobarik.konspekt.domain.models.Note

fun NoteEntity.toDomain() = Note(id, title, note)
fun Note.toEntity() = NoteEntity(id, title, note)
