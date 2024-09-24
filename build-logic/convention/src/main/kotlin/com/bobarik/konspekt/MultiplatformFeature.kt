package com.bobarik.konspekt

import com.bobarik.konspekt.utils.compose
import org.gradle.accessors.dm.LibrariesForLibs
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import org.jetbrains.kotlin.gradle.dsl.KotlinMultiplatformExtension

internal fun Project.configureMultiplatformFeature(
    libs: LibrariesForLibs
) = extensions.configure<KotlinMultiplatformExtension> {
    applyPlatformTargets(libs)

    sourceSets.apply {
        commonMain {
            kotlin.srcDir("build/generated/ksp/metadata/commonMain/kotlin")

            dependencies {
                implementation(project(":core:arch"))
                implementation(project(":core:domain"))
                implementation(project(":core:navigation"))

                implementation(compose.runtime)
                implementation(compose.material3)
                implementation(compose.materialIconsExtended)
                implementation(compose.components.resources)

                implementation(libs.napier)
                implementation(libs.kotlinx.coroutines.core)

                implementation(project.dependencies.platform(libs.koin.bom))
                implementation(libs.koin.core)
                implementation(libs.koin.compose)
                implementation(libs.koin.compose.viewmodel)

                implementation(libs.navigation.compose)

                implementation(libs.androidx.lifecycle.viewmodel)
                implementation(libs.androidx.lifecycle.viewmodel.compose)

                implementation(libs.orbit.core)

                implementation(libs.arrow.core)
                implementation(libs.arrow.coroutines)
                implementation(libs.arrow.optics)

                implementation(libs.kotlinx.serialization.json)
            }
        }

        commonTest.dependencies {
            implementation(kotlin("test"))
        }

        androidMain.dependencies {
            implementation(libs.koin.android)

            implementation(libs.compose.uitooling)
            implementation(libs.kotlinx.coroutines.android)
        }
    }
}
