package com.bobarik.konspekt.home.di

import com.bobarik.konspekt.home.api.HomeFeatureApi
import com.bobarik.konspekt.home.navigation.HomeFeatureApiImpl
import com.bobarik.konspekt.home.ui.mvi.HomeViewModel
import org.koin.core.module.dsl.singleOf
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.bind
import org.koin.dsl.module

val HomeModule = module {
  viewModelOf(::HomeViewModel)
  singleOf(::HomeFeatureApiImpl) bind HomeFeatureApi::class
}
