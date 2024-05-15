package com.archipelago.jobbsyy.root.ui

import androidx.compose.runtime.Composable
import com.archipelago.jobbsyy.home.ui.HomeScreen
import com.archipelago.jobbsyy.login.ui.LoginScreen
import com.archipelago.jobbsyy.root.component.RootComponent
import com.arkivanov.decompose.extensions.compose.stack.Children
import com.arkivanov.decompose.extensions.compose.stack.animation.slide
import com.arkivanov.decompose.extensions.compose.stack.animation.stackAnimation

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
