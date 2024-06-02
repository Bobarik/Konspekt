package com.bobarik.konspekt.root.ui

import androidx.compose.runtime.Composable
import com.arkivanov.decompose.extensions.compose.stack.Children
import com.arkivanov.decompose.extensions.compose.stack.animation.slide
import com.arkivanov.decompose.extensions.compose.stack.animation.stackAnimation
import com.bobarik.konspekt.home.ui.HomeScreen
import com.bobarik.konspekt.login.ui.LoginScreen
import com.bobarik.konspekt.root.component.RootComponent

@Composable
fun RootScreen(
    rootComponent: RootComponent
) = Children(
    stack = rootComponent.childStack,
    animation = stackAnimation(slide())
) {
    when (val child = it.instance) {
        is RootComponent.Child.LoginChild -> LoginScreen(child.component)
        is RootComponent.Child.HomeChild -> HomeScreen(child.component)
    }
}
