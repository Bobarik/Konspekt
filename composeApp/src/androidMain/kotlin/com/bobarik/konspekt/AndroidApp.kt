package com.bobarik.konspekt

import android.app.Application
import com.bobarik.konspekt.di.AppModule
import io.github.aakira.napier.DebugAntilog
import io.github.aakira.napier.Napier
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class AndroidApp : Application() {

  override fun onCreate() {
    super.onCreate()
    Napier.base(DebugAntilog())
    startKoin {
      androidContext(this@AndroidApp)
      modules(
        AppModule,
      )
    }
  }
}
