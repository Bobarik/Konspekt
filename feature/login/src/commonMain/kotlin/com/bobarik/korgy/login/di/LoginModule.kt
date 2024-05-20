package com.bobarik.korgy.login.di

import com.bobarik.korgy.login.component.LoginComponent
import com.bobarik.korgy.login.component.LoginComponentImpl
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.bind
import org.koin.dsl.module

val LoginModule = module {
    factoryOf(::LoginComponentImpl) bind LoginComponent::class
}
