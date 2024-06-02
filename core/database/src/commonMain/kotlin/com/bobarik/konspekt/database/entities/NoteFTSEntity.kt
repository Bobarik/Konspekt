package com.bobarik.konspekt.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Fts4
import androidx.room.PrimaryKey
import com.bobarik.konspekt.domain.models.Note

@Entity(tableName = "notes_fts")
@Fts4(contentEntity = NoteEntity::class)
data class NoteFTSEntity(
    @PrimaryKey @ColumnInfo(name = "rowid") val rowId: Long,
    @ColumnInfo(name = "id") val id: Long,
    @ColumnInfo(name = "title") val title: String,
    @ColumnInfo(name = "note") val note: String
)
