package com.bobarik.konspekt.login.di

import com.bobarik.konspekt.arch.ScreenComponent
import com.bobarik.konspekt.login.api.LoginComponentApi
import com.bobarik.konspekt.login.screen.mvi.LoginStore
import com.bobarik.konspekt.login.screen.ui.LoginComponent
import org.koin.core.module.dsl.factoryOf
import org.koin.core.module.dsl.named
import org.koin.dsl.bind
import org.koin.dsl.module

val LoginModule = module {
  factoryOf(::LoginComponent) { named<LoginComponentApi>() } bind ScreenComponent::class
  factoryOf(::LoginStore)
}
