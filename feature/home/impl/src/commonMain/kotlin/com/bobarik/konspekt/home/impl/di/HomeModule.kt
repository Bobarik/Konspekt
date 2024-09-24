package com.bobarik.konspekt.home.impl.di

import com.bobarik.konspekt.home.api.HomeNavigationApi
import com.bobarik.konspekt.home.impl.component.HomeViewModel
import com.bobarik.konspekt.home.impl.navigation.HomeNavigationImpl
import org.koin.core.module.dsl.singleOf
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.bind
import org.koin.dsl.module

val HomeModule = module {
    viewModelOf(::HomeViewModel)
    singleOf(::HomeNavigationImpl) bind HomeNavigationApi::class
}
