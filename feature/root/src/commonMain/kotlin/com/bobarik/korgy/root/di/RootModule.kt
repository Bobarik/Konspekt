package com.bobarik.korgy.root.di

import com.bobarik.korgy.root.component.RootComponent
import com.bobarik.korgy.root.component.RootComponentImpl
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.bind
import org.koin.dsl.module

val RootModule = module {
    factoryOf(::RootComponentImpl) bind RootComponent::class
}
