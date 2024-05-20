package com.bobarik.korgy.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.bobarik.korgy.database.daos.NoteDao

@Database(
    entities = [Note::class],
    version = 1
)
abstract class KorgyDatabase : RoomDatabase() {

    abstract fun noteDao(): NoteDao
}
