package com.archipelago.jobbsyy.root.di

import com.archipelago.jobbsyy.root.component.RootComponent
import com.archipelago.jobbsyy.root.component.RootComponentImpl
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.bind
import org.koin.dsl.module

val RootModule = module {
    factoryOf(::RootComponentImpl) bind RootComponent::class
}
