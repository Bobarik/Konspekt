package com.bobarik.konspekt.navigation.transitions

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.navigation.NavBackStackEntry

typealias EnterTransitionProvider = AnimatedContentTransitionScope<NavBackStackEntry>.() -> EnterTransition
typealias ExitTransitionProvider = AnimatedContentTransitionScope<NavBackStackEntry>.() -> ExitTransition

interface TransitionStyle {

  val enterTransition: EnterTransitionProvider
  val exitTransition: ExitTransitionProvider
  val popEnterTransition: EnterTransitionProvider get() = enterTransition
  val popExitTransition: ExitTransitionProvider get() = exitTransition
}
