package com.bobarik.korgy.di

import com.bobarik.korgy.database.di.DatabaseModule
import com.bobarik.korgy.home.di.HomeModule
import com.bobarik.korgy.login.di.LoginModule
import com.bobarik.korgy.root.di.RootModule
import org.koin.dsl.module

val AppModule = module {
    includes(RootModule, LoginModule, HomeModule, DatabaseModule)
}
