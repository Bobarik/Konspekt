package com.bobarik.konspekt.navigation

import com.bobarik.konspekt.arch.ScreenComponent
import kotlinx.serialization.Serializable
import kotlin.reflect.KClass

@Serializable
interface ScreenConfig<T : ScreenComponent> {

  val type: KClass<T>
}
