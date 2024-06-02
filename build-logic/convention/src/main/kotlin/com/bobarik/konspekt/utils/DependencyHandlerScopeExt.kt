package com.bobarik.konspekt.utils

import org.gradle.kotlin.dsl.DependencyHandlerScope

fun DependencyHandlerScope.ksp(dependencyNotation: Any) {
    add("kspCommonMainMetadata", dependencyNotation)
}
