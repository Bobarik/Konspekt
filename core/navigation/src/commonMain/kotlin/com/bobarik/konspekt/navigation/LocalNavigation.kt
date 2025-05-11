package com.bobarik.konspekt.navigation

import androidx.compose.runtime.compositionLocalOf
import com.arkivanov.decompose.router.stack.StackNavigation
import com.bobarik.konspekt.arch.ScreenComponent

val LocalNavigation = compositionLocalOf<StackNavigation<ScreenConfig<out ScreenComponent>>> { error("Unable to provide StackNavigation") }
