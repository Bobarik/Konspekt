package com.bobarik.konspekt.arch

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.essenty.lifecycle.coroutines.coroutineScope
import kotlinx.coroutines.Dispatchers
import org.orbitmvi.orbit.container
import kotlin.coroutines.CoroutineContext

abstract class ContainerComponent<STATE : Any, SIDE_EFFECT : Any, EVENT>(
    initState: STATE,
    componentContext: ComponentContext,
    coroutineContext: CoroutineContext = Dispatchers.Main.immediate
) : StateComponent<STATE, SIDE_EFFECT, EVENT>, ComponentContext by componentContext {

    protected val coroutineScope = coroutineScope(coroutineContext)

    override val container = coroutineScope.container<STATE, SIDE_EFFECT>(initialState = initState)

}
