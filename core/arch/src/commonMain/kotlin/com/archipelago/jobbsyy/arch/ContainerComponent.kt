package com.archipelago.jobbsyy.arch

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.essenty.lifecycle.coroutines.coroutineScope
import kotlinx.coroutines.Dispatchers
import org.orbitmvi.orbit.container
import kotlin.coroutines.CoroutineContext

abstract class ContainerComponent<STATE : BaseState, SIDE_EFFECT : BaseSideEffect, EVENT : BaseEvent>(
    initState: STATE,
    componentContext: ComponentContext,
    coroutineContext: CoroutineContext = Dispatchers.Main.immediate
) : StateComponent<STATE, SIDE_EFFECT, EVENT>, ComponentContext by componentContext {

    private val coroutineScope = coroutineScope(coroutineContext)

    override val container = coroutineScope.container<STATE, SIDE_EFFECT>(initialState = initState)

}
