package com.bobarik.konspekt.arch

import androidx.compose.runtime.staticCompositionLocalOf
import com.arkivanov.essenty.lifecycle.LifecycleOwner

val LocalLifecycleOwner = staticCompositionLocalOf<LifecycleOwner> { error("Unable to provide LocalLifecyc") }
