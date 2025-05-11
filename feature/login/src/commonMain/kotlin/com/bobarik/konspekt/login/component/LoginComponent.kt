package com.bobarik.konspekt.login.component

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import com.arkivanov.decompose.ComponentContext
import com.arkivanov.essenty.instancekeeper.getOrCreate
import com.bobarik.konspekt.arch.ScreenComponent
import com.bobarik.konspekt.arch.collectAsState
import com.bobarik.konspekt.arch.collectSideEffect
import com.bobarik.konspekt.login.ui.LoginScreenContent
import com.bobarik.konspekt.login.ui.LoginStore
import org.koin.core.component.get

class LoginComponent(
  componentContext: ComponentContext,
  private val onNavigateHome: () -> Unit,
) : ScreenComponent(componentContext) {

  val store = instanceKeeper.getOrCreate { get<LoginStore>() }

  @Composable
  override fun Content() {
    val state by store.collectAsState()

    LoginScreenContent(
      stateProvider = { state },
      onEvent = store::onEvent,
    )

    store.collectSideEffect { effect ->
      when (effect) {
        is LoginEffect.NavigateHome -> onNavigateHome()
      }
    }
  }
}
