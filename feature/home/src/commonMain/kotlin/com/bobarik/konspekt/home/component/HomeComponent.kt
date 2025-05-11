package com.bobarik.konspekt.home.component

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import com.arkivanov.decompose.ComponentContext
import com.arkivanov.essenty.instancekeeper.getOrCreate
import com.bobarik.konspekt.arch.ScreenComponent
import com.bobarik.konspekt.arch.collectAsState
import com.bobarik.konspekt.arch.collectSideEffect
import com.bobarik.konspekt.home.ui.HomeScreenContent
import com.bobarik.konspekt.home.ui.HomeStore
import org.koin.core.component.get

@Immutable
class HomeComponent(
  componentContext: ComponentContext,
  private val onBackClicked: () -> Unit,
) : ScreenComponent(componentContext) {

  val store = instanceKeeper.getOrCreate { get<HomeStore>() }

  @Composable
  override fun Content() {
    val state = store.collectAsState()

    HomeScreenContent(state, store::onEvent)

    store.collectSideEffect { effect ->
      when (effect) {
        is HomeEffect.Back -> onBackClicked()
      }
    }
  }
}
