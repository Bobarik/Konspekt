package com.bobarik.konspekt.login.di

import com.bobarik.konspekt.login.component.LoginComponent
import com.bobarik.konspekt.login.component.LoginComponentImpl
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.bind
import org.koin.dsl.module

val LoginModule = module {
  factoryOf(::LoginComponentImpl) bind LoginComponent::class
}
