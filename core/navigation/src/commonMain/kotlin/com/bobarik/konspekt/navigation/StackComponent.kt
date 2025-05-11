package com.bobarik.konspekt.navigation

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.router.stack.StackNavigation
import com.arkivanov.decompose.value.Value
import com.bobarik.konspekt.arch.ScreenComponent
import org.koin.core.component.KoinComponent

interface StackComponent<T : Any> : ComponentContext, KoinComponent {

  val navigation: StackNavigation<ScreenConfig<out ScreenComponent>>
  val childStack: Value<ChildStack<*, ScreenComponent>>
}
