package com.bobarik.konspekt.domain.repository

import com.bobarik.konspekt.domain.models.Note
import kotlinx.coroutines.flow.Flow

interface NoteRepository {

    suspend fun upsertNote(note: Note)

    suspend fun deleteNote(note: Note)

    fun getAllNotes(): Flow<List<Note>>

    fun searchNotes(query: String): Flow<List<Note>>
}
