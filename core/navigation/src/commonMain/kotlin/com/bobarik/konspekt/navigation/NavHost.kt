package com.bobarik.konspekt.navigation

import androidx.compose.runtime.compositionLocalOf
import androidx.navigation.NavController

val LocalNavController = compositionLocalOf<NavController> { error("NavController not provided") }