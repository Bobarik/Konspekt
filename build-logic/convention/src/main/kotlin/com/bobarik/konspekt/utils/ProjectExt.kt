package com.bobarik.konspekt.utils

import org.gradle.accessors.dm.LibrariesForLibs
import org.gradle.api.Project
import org.gradle.kotlin.dsl.the
import org.jetbrains.compose.ComposeExtension

internal val Project.libs get() = the<LibrariesForLibs>()
internal val Project.compose get() = the<ComposeExtension>().dependencies
