package com.bobarik.konspekt.login.screen.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.router.stack.pushNew
import com.arkivanov.essenty.instancekeeper.retainedInstance
import com.bobarik.konspekt.arch.collectAsState
import com.bobarik.konspekt.arch.collectSideEffect
import com.bobarik.konspekt.home.api.HomeScreenConfig
import com.bobarik.konspekt.login.api.LoginComponentApi
import com.bobarik.konspekt.login.screen.mvi.LoginEffect
import com.bobarik.konspekt.login.screen.mvi.LoginStore
import com.bobarik.konspekt.navigation.LocalNavigation
import org.koin.core.component.get

class LoginComponent(
  componentContext: ComponentContext,
) : LoginComponentApi(componentContext) {

  val store by lazy { retainedInstance { get<LoginStore>() } }

  @Composable
  override fun Content() {
    val state by store.collectAsState()
    val navigator = LocalNavigation.current

    LoginScreenContent(
      stateProvider = { state },
      onEvent = store::onEvent,
    )

    store.collectSideEffect { effect ->
      when (effect) {
        is LoginEffect.NavigateHome -> navigator.pushNew(HomeScreenConfig)
      }
    }
  }
}
