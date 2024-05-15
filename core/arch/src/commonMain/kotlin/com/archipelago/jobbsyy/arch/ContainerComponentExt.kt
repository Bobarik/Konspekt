package com.archipelago.jobbsyy.arch

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberUpdatedState
import com.arkivanov.essenty.lifecycle.Lifecycle
import com.arkivanov.essenty.lifecycle.coroutines.repeatOnLifecycle
import com.arkivanov.essenty.lifecycle.coroutines.withLifecycle

@Composable
fun <SIDE_EFFECT : BaseSideEffect> StateComponent<*, SIDE_EFFECT, *>.collectSideEffect(
    lifecycleState: Lifecycle.State = Lifecycle.State.STARTED,
    sideEffect: (suspend (sideEffect: SIDE_EFFECT) -> Unit)
) {
    val sideEffectFlow = container.sideEffectFlow
    val lifecycleOwner = this

    val callback by rememberUpdatedState(newValue = sideEffect)

    LaunchedEffect(sideEffectFlow, lifecycleOwner) {
        lifecycleOwner.repeatOnLifecycle(lifecycleState) {
            sideEffectFlow.collect { callback(it) }
        }
    }
}

@Composable
fun <STATE : BaseState> StateComponent<STATE, *, *>.collectState(
    lifecycleState: Lifecycle.State = Lifecycle.State.STARTED
): State<STATE> {
    val stateFlow = container.stateFlow
    val lifecycleOwner = this

    val stateFlowLifecycleAware = remember(stateFlow, lifecycleOwner) {
        stateFlow.withLifecycle(lifecycleOwner.lifecycle, lifecycleState)
    }

    val initialValue = stateFlow.value
    return stateFlowLifecycleAware.collectAsState(initialValue)
}
