package plugins

import com.bobarik.konspekt.configureAndroidApplication
import com.bobarik.konspekt.configureMultiplatformApplication
import com.bobarik.konspekt.utils.apply
import com.bobarik.konspekt.utils.libs
import org.gradle.api.Plugin
import org.gradle.api.Project

class ApplicationConventionPlugin : Plugin<Project> {

  override fun apply(target: Project) = with(target) {
    with(pluginManager) {
      apply(libs.plugins.multiplatform)
      apply(libs.plugins.compose.library)
      apply(libs.plugins.compose.compiler)
      apply(libs.plugins.android.application)
      apply(libs.plugins.buildConfig)
      apply(libs.plugins.kotlinx.serialization)
      apply(libs.plugins.detekt)
    }

    configureMultiplatformApplication(libs)

    configureAndroidApplication()
  }
}
