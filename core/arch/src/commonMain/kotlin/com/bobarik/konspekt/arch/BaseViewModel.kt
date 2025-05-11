package com.bobarik.konspekt.arch

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.container

abstract class BaseViewModel<STATE : Any, SIDE_EFFECT : Any, EVENT>(
  initState: STATE,
) : ContainerHost<STATE, SIDE_EFFECT>, ViewModel() {

  abstract fun onEvent(event: EVENT)

  override val container = viewModelScope.container<STATE, SIDE_EFFECT>(initialState = initState)
}
