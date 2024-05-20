package com.bobarik.korgy

import android.app.Application
import com.bobarik.korgy.di.AppModule
import io.github.aakira.napier.DebugAntilog
import io.github.aakira.napier.Napier
import org.koin.core.context.startKoin

class AndroidApp : Application() {

    override fun onCreate() {
        super.onCreate()
        Napier.base(DebugAntilog())
        startKoin {
            modules(
                AppModule
            )
        }
    }
}
