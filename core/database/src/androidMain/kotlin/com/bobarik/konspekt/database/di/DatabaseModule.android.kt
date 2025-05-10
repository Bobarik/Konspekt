package com.bobarik.konspekt.database.di

import android.content.Context
import androidx.room.Room
import androidx.sqlite.driver.bundled.BundledSQLiteDriver
import com.bobarik.konspekt.database.KonspektDatabase
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module

internal actual val DatabaseBuilderModule = module {
  single { buildKonspektDatabase(androidApplication()) }
}

fun buildKonspektDatabase(context: Context): KonspektDatabase {
  val dbFile = context.getDatabasePath("konspekt.db")
  return Room.databaseBuilder<KonspektDatabase>(
    context = context.applicationContext,
    name = dbFile.absolutePath
  ).setDriver(BundledSQLiteDriver()).build()
}
