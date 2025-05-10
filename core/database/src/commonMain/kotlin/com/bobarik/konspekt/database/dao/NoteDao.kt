package com.bobarik.konspekt.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert
import com.bobarik.konspekt.database.entities.NoteEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface NoteDao {

  @Upsert
  suspend fun upsertNote(note: NoteEntity)

  @Delete
  suspend fun deleteNote(note: NoteEntity)

  @Query("SELECT * from notes")
  fun getAllNotes(): Flow<List<NoteEntity>>

  @Query(
    """
      SELECT * 
      FROM notes_fts
      JOIN notes ON notes.id = notes_fts.id
      WHERE notes_fts MATCH :query
    """
  )
  fun searchNotes(query: String): Flow<List<NoteEntity>>
}
