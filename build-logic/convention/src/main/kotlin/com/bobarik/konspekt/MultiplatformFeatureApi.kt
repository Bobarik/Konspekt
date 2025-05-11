package com.bobarik.konspekt

import com.bobarik.konspekt.utils.apply
import com.bobarik.konspekt.utils.libs
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import org.jetbrains.kotlin.gradle.dsl.KotlinMultiplatformExtension

fun Project.configureMultiplatformFeatureApi() = extensions.configure<KotlinMultiplatformExtension> {
  with(pluginManager) {
    apply(libs.plugins.compose.compiler)
    apply(libs.plugins.compose.library)
  }

  compilerOptions {
    freeCompilerArgs.add("-Xwhen-guards")
    freeCompilerArgs.add("-Xcontext-receivers")
  }

  applyCommonPlatformTargets(libs)

  sourceSets.apply {
    commonMain {
      dependencies {
        implementation(project(":core:navigation"))

        implementation(libs.androidx.navigation)
        implementation(libs.kotlinx.serialization.json)
      }
    }
  }
}
