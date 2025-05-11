package com.bobarik.konspekt.di

import com.bobarik.konspekt.database.di.DatabaseModule
import com.bobarik.konspekt.home.di.HomeModule
import com.bobarik.konspekt.login.di.LoginModule
import org.koin.dsl.module

val AppModule = module {
  includes(
    LoginModule,
    HomeModule,
    DatabaseModule,
  )
}
