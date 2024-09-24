package com.bobarik.konspekt.arch

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.container

abstract class MviViewModel<STATE : BaseState, SIDE_EFFECT : BaseSideEffect, EVENT : BaseEvent>(
    initState: STATE
) : ContainerHost<STATE, SIDE_EFFECT>, ViewModel() {

    override val container = viewModelScope.container<STATE, SIDE_EFFECT>(initialState = initState)

    abstract fun onEvent(event: EVENT)

}
