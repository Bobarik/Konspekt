package com.bobarik.konspekt.arch

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import com.arkivanov.decompose.ComponentContext
import kotlinx.serialization.Serializable
import org.koin.core.component.KoinComponent

@Serializable
abstract class ScreenComponent(
  context: ComponentContext,
) : KoinComponent, ComponentContext by context {

  @Composable
  abstract fun Content()

  @Composable
  operator fun invoke() {
    CompositionLocalProvider(LocalLifecycleOwner provides this) {
      Content()
    }
  }
}
