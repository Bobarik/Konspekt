package com.bobarik.konspekt.home.di

import com.bobarik.konspekt.home.component.HomeComponent
import com.bobarik.konspekt.home.ui.HomeStore
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.bind
import org.koin.dsl.module

val HomeModule = module {
  factoryOf(::HomeComponent) bind HomeComponent::class
  factoryOf(::HomeStore)
}
