package com.bobarik.konspekt.arch

import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.produceState
import com.arkivanov.essenty.lifecycle.Lifecycle
import com.arkivanov.essenty.lifecycle.LifecycleOwner
import com.arkivanov.essenty.lifecycle.coroutines.repeatOnLifecycle
import kotlin.coroutines.CoroutineContext
import kotlin.coroutines.EmptyCoroutineContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.withContext

@Composable
fun <T> Flow<T>.collectAsStateWithLifecycle(
  initialValue: T,
  lifecycleOwner: LifecycleOwner = LocalLifecycleOwner.current,
  minActiveState: Lifecycle.State = Lifecycle.State.STARTED,
  context: CoroutineContext = EmptyCoroutineContext,
): State<T> {
  return produceState(initialValue, this, lifecycleOwner.lifecycle, minActiveState, context) {
    lifecycleOwner.lifecycle.repeatOnLifecycle(minActiveState) {
      if (context == EmptyCoroutineContext) {
        this@collectAsStateWithLifecycle.collect { this@produceState.value = it }
      } else
        withContext(context) {
          this@collectAsStateWithLifecycle.collect { this@produceState.value = it }
        }
    }
  }
}

@Composable
fun <T> StateFlow<T>.collectAsStateWithLifecycle(
  lifecycleOwner: LifecycleOwner = LocalLifecycleOwner.current,
  minActiveState: Lifecycle.State = Lifecycle.State.STARTED,
  context: CoroutineContext = EmptyCoroutineContext,
): State<T> = collectAsStateWithLifecycle(
  initialValue = value,
  lifecycleOwner = lifecycleOwner,
  minActiveState = minActiveState,
  context = context,
)

