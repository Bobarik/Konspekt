package com.bobarik.konspekt

import com.bobarik.konspekt.utils.libs
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import org.jetbrains.kotlin.gradle.dsl.KotlinMultiplatformExtension

fun Project.configureMultiplatformFeature() = extensions.configure<KotlinMultiplatformExtension> {

  applyPlatformTargets(libs)

  compilerOptions {
    freeCompilerArgs.add("-Xwhen-guards")
    freeCompilerArgs.add("-Xcontext-receivers")
  }

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
        implementation(libs.koin.compose)
        implementation(libs.koin.compose.viewmodel)

        implementation(libs.androidx.navigation)

        implementation(libs.orbit.core)

        implementation(libs.arrow.core)
        implementation(libs.arrow.coroutines)
        implementation(libs.arrow.optics)
        implementation(libs.quiver)
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
