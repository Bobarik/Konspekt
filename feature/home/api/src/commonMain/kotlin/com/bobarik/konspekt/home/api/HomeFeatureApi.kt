package com.bobarik.konspekt.home.api

import com.arkivanov.decompose.ComponentContext
import com.bobarik.konspekt.arch.ScreenComponent
import com.bobarik.konspekt.navigation.ScreenConfig
import kotlin.reflect.KClass
import kotlinx.serialization.Serializable

abstract class HomeComponentApi(context: ComponentContext) : ScreenComponent(context)

@Serializable
data object HomeScreenConfig : ScreenConfig<HomeComponentApi> {

  override val type: KClass<HomeComponentApi> = HomeComponentApi::class
}
