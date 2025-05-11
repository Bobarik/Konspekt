package com.bobarik.konspekt.navigation

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.value.Value
import org.koin.core.component.KoinComponent

interface StackComponent<T : Any> : ComponentContext, KoinComponent {

  val childStack: Value<ChildStack<*, T>>
}
