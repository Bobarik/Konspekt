package com.bobarik.konspekt.root.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import com.arkivanov.decompose.extensions.compose.stack.Children
import com.arkivanov.decompose.extensions.compose.stack.animation.slide
import com.arkivanov.decompose.extensions.compose.stack.animation.stackAnimation
import com.bobarik.konspekt.navigation.LocalNavigation
import com.bobarik.konspekt.root.component.RootComponent

@Composable
fun RootScreen(
  rootComponent: RootComponent,
) {
  CompositionLocalProvider(
    LocalNavigation provides rootComponent.navigation,
  ) {
    Children(
      stack = rootComponent.childStack,
      animation = stackAnimation(slide()),
    ) { value ->
      value.instance()
    }
  }
}
