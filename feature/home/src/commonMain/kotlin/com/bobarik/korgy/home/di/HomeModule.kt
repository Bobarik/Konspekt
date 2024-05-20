package com.bobarik.korgy.home.di

import com.bobarik.korgy.home.component.HomeComponent
import com.bobarik.korgy.home.component.HomeComponentImpl
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.bind
import org.koin.dsl.module

val HomeModule = module {
    factoryOf(::HomeComponentImpl) bind HomeComponent::class
}
