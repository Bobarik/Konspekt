package com.bobarik.konspekt.home.di

import com.bobarik.konspekt.home.navigation.HomeFeatureGraphImpl
import com.bobarik.konspekt.home.ui.mvi.HomeViewModel
import com.bobarik.konspekt.navigation.FeatureGraph
import org.koin.core.module.dsl.singleOf
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.bind
import org.koin.dsl.module

val HomeModule = module {
  viewModelOf(::HomeViewModel)
  singleOf(::HomeFeatureGraphImpl) bind FeatureGraph::class
}
