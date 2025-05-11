package com.bobarik.konspekt.login.di

import com.bobarik.konspekt.login.api.LoginFeatureApi
import com.bobarik.konspekt.login.navigation.LoginFeatureApiImpl
import com.bobarik.konspekt.login.screen.mvi.LoginViewModel
import org.koin.core.module.dsl.singleOf
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.bind
import org.koin.dsl.module

val LoginModule = module {
  viewModelOf(::LoginViewModel)
  singleOf(::LoginFeatureApiImpl) bind LoginFeatureApi::class
}
