package com.bobarik.konspekt

import com.bobarik.konspekt.utils.apply
import org.gradle.accessors.dm.LibrariesForLibs
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import org.jetbrains.kotlin.gradle.dsl.KotlinMultiplatformExtension

fun Project.configureMultiplatformFeatureApi(
    libs: LibrariesForLibs
) = extensions.configure<KotlinMultiplatformExtension> {
    with(pluginManager) {
        apply(libs.plugins.compose.compiler)
        apply(libs.plugins.compose.library)
    }

    jvm()

    sourceSets.apply {
        commonMain {
            dependencies {
                implementation(project(":core:navigation"))

                implementation(libs.navigation.compose)
                implementation(libs.kotlinx.serialization.json)
            }
        }
    }
}
