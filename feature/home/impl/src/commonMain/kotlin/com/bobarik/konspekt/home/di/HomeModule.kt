package com.bobarik.konspekt.home.di

import com.bobarik.konspekt.arch.ScreenComponent
import com.bobarik.konspekt.home.api.HomeComponentApi
import com.bobarik.konspekt.home.ui.HomeComponent
import com.bobarik.konspekt.home.ui.mvi.HomeStore
import org.koin.core.module.dsl.factoryOf
import org.koin.core.module.dsl.named
import org.koin.dsl.bind
import org.koin.dsl.module

val HomeModule = module {
  factoryOf(::HomeComponent) { named<HomeComponentApi>() } bind ScreenComponent::class
  factoryOf(::HomeStore)
}
