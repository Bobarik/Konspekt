package com.bobarik.konspekt.home.ui

import androidx.compose.runtime.Composable
import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.router.stack.pop
import com.arkivanov.essenty.instancekeeper.retainedInstance
import com.bobarik.konspekt.arch.collectAsState
import com.bobarik.konspekt.arch.collectSideEffect
import com.bobarik.konspekt.home.api.HomeComponentApi
import com.bobarik.konspekt.home.ui.mvi.HomeEffect
import com.bobarik.konspekt.home.ui.mvi.HomeStore
import com.bobarik.konspekt.navigation.LocalNavigation
import org.koin.core.component.get

class HomeComponent(
  componentContext: ComponentContext,
) : HomeComponentApi(componentContext) {

  val store by lazy { retainedInstance { get<HomeStore>() } }

  @Composable
  override fun Content() {
    val state = store.collectAsState()
    val navigator = LocalNavigation.current

    HomeScreenContent(state, store::onEvent)

    store.collectSideEffect { effect ->
      when (effect) {
        is HomeEffect.Back -> navigator.pop()
      }
    }
  }
}
