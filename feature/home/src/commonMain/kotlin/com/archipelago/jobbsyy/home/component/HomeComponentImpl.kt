package com.archipelago.jobbsyy.home.component

import com.archipelago.jobbsyy.arch.ContainerComponent
import com.arkivanov.decompose.ComponentContext

class HomeComponentImpl(
    componentContext: ComponentContext,
    private val onBackClicked: () -> Unit
) : ContainerComponent<HomeState, Nothing, Nothing>(
    initState = HomeState(),
    componentContext = componentContext
), HomeComponent {

    override fun onEvent(event: Nothing) {
        //TODO
    }

    private fun onNavigateBack() = onBackClicked()
}
