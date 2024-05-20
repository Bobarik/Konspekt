package plugins

import com.bobarik.konspekt.configureAndroidLibrary
import com.bobarik.konspekt.configureMultiplatformFeature
import com.bobarik.konspekt.libs
import org.gradle.api.Plugin
import org.gradle.api.Project

class FeatureConventionPlugin : Plugin<Project> {

    override fun apply(target: Project) = with(target) {
        with(pluginManager) {
            apply(libs.plugins.multiplatform.get().pluginId)
            apply(libs.plugins.android.library.get().pluginId)
            apply(libs.plugins.compose.library.get().pluginId)
            apply(libs.plugins.compose.compiler.get().pluginId)
            apply(libs.plugins.kotlinx.serialization.get().pluginId)
        }

        configureMultiplatformFeature(libs)

        configureAndroidLibrary()
    }
}
