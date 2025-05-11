package com.bobarik.konspekt.arch

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberUpdatedState
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.repeatOnLifecycle
import kotlin.coroutines.CoroutineContext
import kotlinx.coroutines.Dispatchers
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.annotation.OrbitDsl

@OrbitDsl
fun <STATE : Any> ContainerHost<STATE, *>.reduce(
  transform: STATE.() -> STATE,
) = intent { reduce { state.transform() } }

@OrbitDsl
fun <STATE : Any> ContainerHost<STATE, *>.blockingReduce(
  transform: STATE.() -> STATE,
) = blockingIntent { reduce { state.transform() } }

@OrbitDsl
fun <SIDE_EFFECT : Any> ContainerHost<*, SIDE_EFFECT>.postEffect(
  sideEffect: SIDE_EFFECT,
) = intent { postSideEffect(sideEffect) }

@Composable
fun <STATE : Any, SIDE_EFFECT : Any> ContainerHost<STATE, SIDE_EFFECT>.collectAsState(
  lifecycleOwner: LifecycleOwner = LocalLifecycleOwner.current,
  minActiveState: Lifecycle.State = Lifecycle.State.STARTED,
  context: CoroutineContext = Dispatchers.Main.immediate,
): State<STATE> = container.refCountStateFlow.collectAsStateWithLifecycle(lifecycleOwner, minActiveState, context)

@Composable
fun <STATE : Any, SIDE_EFFECT : Any> ContainerHost<STATE, SIDE_EFFECT>.collectSideEffect(
  lifecycleOwner: LifecycleOwner = LocalLifecycleOwner.current,
  minActiveState: Lifecycle.State = Lifecycle.State.STARTED,
  sideEffect: (suspend (sideEffect: SIDE_EFFECT) -> Unit),
) {
  val sideEffectFlow = container.sideEffectFlow
  val callback by rememberUpdatedState(newValue = sideEffect)

  LaunchedEffect(sideEffectFlow, lifecycleOwner) {
    lifecycleOwner.repeatOnLifecycle(minActiveState) {
      sideEffectFlow.collect {
        callback(it)
      }
    }
  }
}
