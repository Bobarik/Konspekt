package plugins

import com.bobarik.konspekt.configureCommonMultiplatformTargets
import com.bobarik.konspekt.utils.apply
import com.bobarik.konspekt.utils.libs
import org.gradle.api.Plugin
import org.gradle.api.Project

class CommonLibraryConventionPlugin : Plugin<Project> {

  override fun apply(target: Project) = with(target) {
    with(pluginManager) {
      apply(libs.plugins.multiplatform)
      apply(libs.plugins.detekt)
    }

    configureCommonMultiplatformTargets()
  }
}
