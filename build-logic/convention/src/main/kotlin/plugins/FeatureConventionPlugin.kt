package plugins

import com.bobarik.konspekt.configureAndroidLibrary
import com.bobarik.konspekt.configureMultiplatformFeature
import com.bobarik.konspekt.utils.apply
import com.bobarik.konspekt.utils.ksp
import com.bobarik.konspekt.utils.libs
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.withType
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

class FeatureConventionPlugin : Plugin<Project> {

  override fun apply(target: Project) = with(target) {
    with(pluginManager) {
      apply(libs.plugins.multiplatform)
      apply(libs.plugins.android.library)
      apply(libs.plugins.compose.library)
      apply(libs.plugins.compose.compiler)
      apply(libs.plugins.kotlinx.serialization)
      apply(libs.plugins.ksp)
      apply(libs.plugins.detekt)
    }

    configureMultiplatformFeature()

    configureAndroidLibrary()

    dependencies {
      ksp(libs.arrow.optics.ksp)
    }

    tasks.withType<KotlinCompile>().all {
      if (name != "kspCommonMainKotlinMetadata") {
        dependsOn("kspCommonMainKotlinMetadata")
      }
    }
  }
}
