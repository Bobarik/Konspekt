package com.archipelago.jobbsyy.di

import com.archipelago.jobbsyy.home.di.HomeModule
import com.archipelago.jobbsyy.login.di.LoginModule
import com.archipelago.jobbsyy.root.di.RootModule
import org.koin.dsl.module

val AppModule = module {
    includes(RootModule, LoginModule, HomeModule)
}
