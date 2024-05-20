package com.bobarik.konspekt.database.di

import android.content.Context
import androidx.room.Room
import androidx.sqlite.driver.bundled.BundledSQLiteDriver
import com.bobarik.konspekt.database.KorgyDatabase
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module

internal actual val DatabaseBuilderModule = module {
    single { buildKorgyDatabase(androidApplication()) }
}

fun buildKorgyDatabase(context: Context): KorgyDatabase {
    val dbFile = context.getDatabasePath("konspekt.db")
    return Room.databaseBuilder<KorgyDatabase>(
        context = context.applicationContext,
        name = dbFile.absolutePath
    )
        .setDriver(BundledSQLiteDriver())
        .build()
}
