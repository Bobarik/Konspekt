package com.bobarik.konspekt.arch

import com.arkivanov.essenty.instancekeeper.InstanceKeeper
import kotlin.coroutines.CoroutineContext
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancel
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.container

abstract class BaseStore<STATE : Any, SIDE_EFFECT : Any, EVENT>(
  initState: STATE,
  context: CoroutineContext = Dispatchers.Main.immediate,
) : ContainerHost<STATE, SIDE_EFFECT>, InstanceKeeper.Instance {

  protected val coroutineScope = CoroutineScope(context)

  abstract fun onEvent(event: EVENT)

  override val container = coroutineScope.container<STATE, SIDE_EFFECT>(initialState = initState)

  override fun onDestroy() {
    println("${this::class.simpleName} IS BEING DESTROYED...")
    coroutineScope.cancel()
  }
}
