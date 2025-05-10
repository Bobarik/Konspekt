package com.bobarik.konspekt.database

import com.bobarik.konspekt.database.dao.NoteDao
import com.bobarik.konspekt.database.entities.NoteEntity
import com.bobarik.konspekt.database.mappers.toDomain
import com.bobarik.konspekt.database.mappers.toEntity
import com.bobarik.konspekt.domain.models.Note
import com.bobarik.konspekt.domain.repository.NoteRepository
import kotlinx.coroutines.flow.map

class NoteRepositoryImpl(
  private val dao: NoteDao,
) : NoteRepository {

  override suspend fun upsertNote(note: Note) = dao.upsertNote(note.toEntity())

  override suspend fun deleteNote(note: Note) = dao.deleteNote(note.toEntity())

  override fun getAllNotes() = dao.getAllNotes().map { list ->
    list.map(NoteEntity::toDomain)
  }

  override fun searchNotes(query: String) = dao.searchNotes("*$query*").map { list ->
    list.map(NoteEntity::toDomain)
  }
}
