package plugins

import com.bobarik.korgy.configureAndroidApplication
import com.bobarik.korgy.configureMultiplatformApplication
import com.bobarik.korgy.libs
import org.gradle.api.Plugin
import org.gradle.api.Project

class ApplicationConventionPlugin : Plugin<Project> {

    override fun apply(target: Project) = with(target) {
        with(pluginManager) {
            apply(libs.plugins.multiplatform.get().pluginId)
            apply(libs.plugins.compose.library.get().pluginId)
            apply(libs.plugins.compose.compiler.get().pluginId)
            apply(libs.plugins.android.application.get().pluginId)
            apply(libs.plugins.buildConfig.get().pluginId)
            apply(libs.plugins.kotlinx.serialization.get().pluginId)
        }

        configureMultiplatformApplication(libs)

        configureAndroidApplication()
    }
}
