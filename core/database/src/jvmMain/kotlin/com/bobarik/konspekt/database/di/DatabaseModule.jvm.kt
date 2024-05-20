package com.bobarik.konspekt.database.di

import androidx.room.Room
import androidx.sqlite.driver.bundled.BundledSQLiteDriver
import com.bobarik.konspekt.database.KorgyDatabase
import org.koin.dsl.module
import java.io.File

internal actual val DatabaseBuilderModule = module {
    single { buildKorgyDatabase() }
}

fun buildKorgyDatabase(): KorgyDatabase {
    val dbFile = File(System.getProperty("java.io.tmpdir"), "my_room.db")
    return Room.databaseBuilder<KorgyDatabase>(
        name = dbFile.absolutePath,
    ).setDriver(BundledSQLiteDriver()).build()
}
