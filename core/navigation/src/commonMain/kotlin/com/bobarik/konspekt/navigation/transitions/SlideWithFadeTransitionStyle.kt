package com.bobarik.konspekt.navigation.transitions

import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.ui.unit.IntOffset

private const val TransitionDuration = 400

object SlideWithFadeTransitionStyle : TransitionStyle {

  private val slideSpec = tween<IntOffset>(TransitionDuration)
  private val fadeInSpec = tween<Float>(TransitionDuration / 4 * 3, delayMillis = TransitionDuration / 4)
  private val fadeOutSpec = tween<Float>(TransitionDuration / 4 * 3)

  override val enterTransition: EnterTransitionProvider = {
    slideInHorizontally(slideSpec) { fullWidth -> fullWidth } + fadeIn(fadeInSpec)
  }

  override val exitTransition: ExitTransitionProvider = {
    slideOutHorizontally(slideSpec) { fullWidth -> -fullWidth } + fadeOut(fadeOutSpec)
  }

  override val popEnterTransition: EnterTransitionProvider = {
    slideInHorizontally(slideSpec) { fullWidth -> -fullWidth } + fadeIn(fadeInSpec)
  }

  override val popExitTransition: ExitTransitionProvider = {
    slideOutHorizontally(slideSpec) { fullWidth -> fullWidth } + fadeOut(fadeOutSpec)
  }
}
