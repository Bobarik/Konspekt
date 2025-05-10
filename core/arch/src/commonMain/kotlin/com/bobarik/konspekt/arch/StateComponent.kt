package com.bobarik.konspekt.arch

import com.arkivanov.decompose.ComponentContext
import org.orbitmvi.orbit.ContainerHost

interface StateComponent<STATE : Any, SIDE_EFFECT : Any, EVENT>
  : ComponentContext, ContainerHost<STATE, SIDE_EFFECT> {

  fun onEvent(event: EVENT)
}
