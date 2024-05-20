package com.bobarik.konspekt.di

import com.bobarik.konspekt.database.di.DatabaseModule
import com.bobarik.konspekt.home.di.HomeModule
import com.bobarik.konspekt.login.di.LoginModule
import com.bobarik.konspekt.root.di.RootModule
import org.koin.dsl.module

val AppModule = module {
    includes(RootModule, LoginModule, HomeModule, DatabaseModule)
}
