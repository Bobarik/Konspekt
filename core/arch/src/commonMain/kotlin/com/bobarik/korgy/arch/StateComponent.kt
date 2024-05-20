package com.bobarik.korgy.arch

import com.arkivanov.decompose.ComponentContext
import org.orbitmvi.orbit.ContainerHost

interface StateComponent<STATE : BaseState, SIDE_EFFECT : BaseSideEffect, EVENT : BaseEvent>
    : ComponentContext, ContainerHost<STATE, SIDE_EFFECT> {

    fun onEvent(event: EVENT)
}
