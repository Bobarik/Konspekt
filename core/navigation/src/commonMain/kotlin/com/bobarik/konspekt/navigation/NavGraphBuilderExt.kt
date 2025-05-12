package com.bobarik.konspekt.navigation

import androidx.compose.animation.AnimatedContentScope
import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.SizeTransform
import androidx.compose.runtime.Composable
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavDeepLink
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import com.bobarik.konspekt.navigation.transitions.SlideWithFadeTransitionStyle
import com.bobarik.konspekt.navigation.transitions.TransitionStyle
import kotlin.reflect.KType

inline fun <reified T : Any> NavGraphBuilder.composable(
  typeMap: Map<KType, @JvmSuppressWildcards NavType<*>> = emptyMap(),
  deepLinks: List<NavDeepLink> = emptyList(),
  transitionStyle: TransitionStyle = SlideWithFadeTransitionStyle,
  noinline sizeTransform: (AnimatedContentTransitionScope<NavBackStackEntry>.() -> @JvmSuppressWildcards SizeTransform?)? = null,
  noinline content: @Composable AnimatedContentScope.(NavBackStackEntry) -> Unit,
) = composable(
  route = T::class,
  typeMap = typeMap,
  deepLinks = deepLinks,
  enterTransition = transitionStyle.enterTransition,
  exitTransition = transitionStyle.exitTransition,
  popEnterTransition = transitionStyle.popEnterTransition,
  popExitTransition = transitionStyle.popExitTransition,
  sizeTransform = sizeTransform,
  content = content,
)
