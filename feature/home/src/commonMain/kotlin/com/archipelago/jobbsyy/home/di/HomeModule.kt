package com.archipelago.jobbsyy.home.di

import com.archipelago.jobbsyy.home.component.HomeComponent
import com.archipelago.jobbsyy.home.component.HomeComponentImpl
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.bind
import org.koin.dsl.module

val HomeModule = module {
    factoryOf(::HomeComponentImpl) bind HomeComponent::class
}
