package com.archipelago.jobbsyy.root.component

import com.archipelago.jobbsyy.home.component.HomeComponent
import com.archipelago.jobbsyy.login.component.LoginComponent
import com.archipelago.jobbsyy.root.component.RootComponent.Child.HomeChild
import com.archipelago.jobbsyy.root.component.RootComponent.Child.LoginChild
import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.router.stack.StackNavigation
import com.arkivanov.decompose.router.stack.childStack
import com.arkivanov.decompose.router.stack.pop
import com.arkivanov.decompose.router.stack.push
import com.arkivanov.decompose.value.Value
import kotlinx.serialization.Serializable
import org.koin.core.component.get
import org.koin.core.parameter.parametersOf

class RootComponentImpl(
    componentContext: ComponentContext
) : RootComponent, ComponentContext by componentContext {

    private val navigation = StackNavigation<Config>()

    override val childStack: Value<ChildStack<*, RootComponent.Child>> = childStack(
        source = navigation,
        serializer = Config.serializer(),
        initialConfiguration = Config.Login,
        handleBackButton = true,
        childFactory = ::createChild,
    )

    private fun createChild(
        config: Config,
        componentContext: ComponentContext
    ): RootComponent.Child =
        when (config) {
            is Config.Login -> LoginChild(itemLogin(componentContext))
            is Config.Home -> HomeChild(itemHome(componentContext))
        }

    private fun itemLogin(
        componentContext: ComponentContext
    ) = get<LoginComponent> { parametersOf(componentContext, ::onHome) }

    private fun itemHome(
        componentContext: ComponentContext
    ) = get<HomeComponent> { parametersOf(componentContext, ::onBack) }

    private fun onBack() = navigation.pop()

    private fun onHome() = navigation.push(Config.Home)

    @Serializable
    private sealed interface Config {

        @Serializable
        data object Login : Config

        @Serializable
        data object Home : Config
    }

}
