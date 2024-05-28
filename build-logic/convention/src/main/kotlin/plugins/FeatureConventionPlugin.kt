package plugins

import com.bobarik.konspekt.apply
import com.bobarik.konspekt.configureAndroidLibrary
import com.bobarik.konspekt.configureMultiplatformFeature
import com.bobarik.konspekt.libs
import org.gradle.api.Plugin
import org.gradle.api.Project

class FeatureConventionPlugin : Plugin<Project> {

    override fun apply(target: Project) = with(target) {
        with(pluginManager) {
            apply(libs.plugins.multiplatform)
            apply(libs.plugins.android.library)
            apply(libs.plugins.compose.library)
            apply(libs.plugins.compose.compiler)
            apply(libs.plugins.kotlinx.serialization)
        }

        configureMultiplatformFeature(libs)

        configureAndroidLibrary(libs = libs)
    }
}
