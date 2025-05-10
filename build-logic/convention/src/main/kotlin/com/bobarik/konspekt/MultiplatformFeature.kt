package com.bobarik.konspekt

import com.bobarik.konspekt.utils.libs
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import org.jetbrains.kotlin.gradle.dsl.KotlinMultiplatformExtension

fun Project.configureMultiplatformFeature() = extensions.configure<KotlinMultiplatformExtension> {

  applyPlatformTargets(libs)

  sourceSets.apply {
    commonMain {
      kotlin.srcDir("build/generated/ksp/metadata/commonMain/kotlin")

      dependencies {
        implementation(project(":core:arch"))
        implementation(project(":core:domain"))
        implementation(project(":core:navigation"))

        implementation(libs.napier)
        implementation(libs.kotlinx.coroutines.core)

        implementation(project.dependencies.platform(libs.koin.bom))
        implementation(libs.koin.core)
        implementation(libs.koin.compose)

        implementation(libs.decompose)
        implementation(libs.decompose.compose)

        implementation(libs.orbit.core)

        implementation(libs.arrow.core)
        implementation(libs.arrow.coroutines)
        implementation(libs.arrow.optics)
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
