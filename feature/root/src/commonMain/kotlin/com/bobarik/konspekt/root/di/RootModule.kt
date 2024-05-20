package com.bobarik.konspekt.root.di

import com.bobarik.konspekt.root.component.RootComponent
import com.bobarik.konspekt.root.component.RootComponentImpl
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.bind
import org.koin.dsl.module

val RootModule = module {
    factoryOf(::RootComponentImpl) bind RootComponent::class
}
