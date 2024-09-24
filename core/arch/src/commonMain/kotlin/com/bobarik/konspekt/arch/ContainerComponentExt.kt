package com.bobarik.konspekt.arch

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberUpdatedState
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.repeatOnLifecycle

@Composable
fun <SIDE_EFFECT : BaseSideEffect> MviViewModel<*, SIDE_EFFECT, *>.collectSideEffect(
    lifecycleState: Lifecycle.State = Lifecycle.State.STARTED,
    sideEffect: (suspend (sideEffect: SIDE_EFFECT) -> Unit)
) {
    val sideEffectFlow = container.sideEffectFlow
    val lifecycleOwner = LocalLifecycleOwner.current

    val callback by rememberUpdatedState(newValue = sideEffect)

    LaunchedEffect(sideEffectFlow, lifecycleOwner) {
        lifecycleOwner.repeatOnLifecycle(lifecycleState) {
            sideEffectFlow.collect { callback(it) }
        }
    }
}

@Composable
fun <STATE : BaseState> MviViewModel<STATE, *, *>.collectState(
    lifecycleState: Lifecycle.State = Lifecycle.State.STARTED
): State<STATE> {
    val stateFlow = container.stateFlow
    val lifecycleOwner = LocalLifecycleOwner.current

    val stateFlowLifecycleAware = remember(stateFlow, lifecycleOwner) {
        stateFlow.flowWithLifecycle(lifecycleOwner.lifecycle, lifecycleState)
    }

    val initialValue = stateFlow.value
    return stateFlowLifecycleAware.collectAsState(initialValue)
}
