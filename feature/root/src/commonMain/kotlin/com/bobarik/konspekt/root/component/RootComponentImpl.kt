package com.bobarik.konspekt.root.component

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.router.stack.StackNavigation
import com.arkivanov.decompose.router.stack.childStack
import com.arkivanov.decompose.value.Value
import com.bobarik.konspekt.arch.ScreenComponent
import com.bobarik.konspekt.login.api.LoginScreenConfig
import com.bobarik.konspekt.navigation.ScreenConfig
import org.koin.core.component.get
import org.koin.core.qualifier.TypeQualifier

class RootComponentImpl(
  componentContext: ComponentContext,
) : RootComponent, ComponentContext by componentContext {

  override val navigation = StackNavigation<ScreenConfig<out ScreenComponent>>()

  override val childStack: Value<ChildStack<*, ScreenComponent>> = childStack(
    source = navigation,
    serializer = null,
    initialConfiguration = LoginScreenConfig,
    handleBackButton = true,
    childFactory = { config, ctx -> get(qualifier = TypeQualifier(config.type)) },
  )
}
