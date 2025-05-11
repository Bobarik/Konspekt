package com.bobarik.konspekt.root.ui

import androidx.compose.runtime.Composable
import com.arkivanov.decompose.extensions.compose.stack.Children
import com.arkivanov.decompose.extensions.compose.stack.animation.slide
import com.arkivanov.decompose.extensions.compose.stack.animation.stackAnimation
import com.bobarik.konspekt.root.component.RootComponent

@Composable
fun RootScreen(
  rootComponent: RootComponent,
) = Children(
  stack = rootComponent.childStack,
  animation = stackAnimation(slide()),
) { value ->
  value.instance()
}
