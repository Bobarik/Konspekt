package com.bobarik.konspekt

import org.gradle.accessors.dm.LibrariesForLibs
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import org.jetbrains.kotlin.gradle.dsl.KotlinMultiplatformExtension

fun Project.configureMultiplatformApplication(
    libs: LibrariesForLibs
) = extensions.configure<KotlinMultiplatformExtension> {
    androidTarget()
    jvm()
    applyDefaultHierarchyTemplate()

    jvmToolchain(libs.versions.java.get().toInt())

    sourceSets.apply {
        commonMain.dependencies {
            implementation(libs.napier)

            implementation(libs.kotlinx.coroutines.core)

            implementation(libs.ktor.core)

            implementation(project.dependencies.platform(libs.koin.bom))
            implementation(libs.koin.core)

            implementation(libs.decompose)
            implementation(libs.essenty.lifecycle)
        }

        commonTest.dependencies {
            implementation(kotlin("test"))
        }

        androidMain.dependencies {
            implementation(libs.androidx.appcompat)
            implementation(libs.androidx.activityCompose)
            implementation(libs.compose.uitooling)
            implementation(libs.kotlinx.coroutines.android)
            implementation(libs.koin.android)
        }
    }
}
