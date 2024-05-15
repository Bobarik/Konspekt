package com.archipelago.jobbsyy.login.di

import com.archipelago.jobbsyy.login.component.LoginComponent
import com.archipelago.jobbsyy.login.component.LoginComponentImpl
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.bind
import org.koin.dsl.module

val LoginModule = module {
    factoryOf(::LoginComponentImpl) bind LoginComponent::class
}
