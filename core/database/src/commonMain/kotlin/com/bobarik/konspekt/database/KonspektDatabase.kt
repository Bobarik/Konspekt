package com.bobarik.konspekt.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.bobarik.konspekt.database.dao.NoteDao
import com.bobarik.konspekt.database.entities.NoteEntity
import com.bobarik.konspekt.database.entities.NoteFTSEntity

@Database(
  entities = [NoteEntity::class, NoteFTSEntity::class],
  version = 1,
)
abstract class KonspektDatabase : RoomDatabase() {

  abstract fun noteDao(): NoteDao
}
