package plugins

import com.bobarik.konspekt.configureMultiplatformFeatureApi
import com.bobarik.konspekt.utils.apply
import com.bobarik.konspekt.utils.libs
import org.gradle.api.Plugin
import org.gradle.api.Project

class FeatureApiConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) = with(target) {
        with(pluginManager) {
            apply(libs.plugins.multiplatform)
            apply(libs.plugins.kotlinx.serialization)
        }

        configureMultiplatformFeatureApi(libs)
    }
}