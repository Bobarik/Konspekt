package com.bobarik.konspekt.login.api

import com.arkivanov.decompose.ComponentContext
import com.bobarik.konspekt.arch.ScreenComponent
import com.bobarik.konspekt.navigation.ScreenConfig
import kotlin.reflect.KClass
import kotlinx.serialization.Serializable

abstract class LoginComponentApi(context: ComponentContext) : ScreenComponent(context)

@Serializable
data object LoginScreenConfig : ScreenConfig<LoginComponentApi> {

  override val type: KClass<LoginComponentApi> = LoginComponentApi::class
}
