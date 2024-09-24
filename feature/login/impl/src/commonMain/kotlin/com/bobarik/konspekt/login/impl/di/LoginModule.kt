package com.bobarik.konspekt.login.impl.di

import com.bobarik.konspekt.home.api.LoginNavigationApi
import com.bobarik.konspekt.login.impl.component.LoginViewModel
import com.bobarik.konspekt.login.impl.navigation.LoginNavigationImpl
import org.koin.core.module.dsl.singleOf
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.bind
import org.koin.dsl.module

val LoginModule = module {
    viewModelOf(::LoginViewModel)
    singleOf(::LoginNavigationImpl) bind LoginNavigationApi::class
}
