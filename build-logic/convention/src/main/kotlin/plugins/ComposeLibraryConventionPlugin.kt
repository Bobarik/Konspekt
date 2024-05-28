package plugins

import com.bobarik.konspekt.apply
import com.bobarik.konspekt.configureAndroidLibrary
import com.bobarik.konspekt.configureMultiplatformTargets
import com.bobarik.konspekt.libs
import org.gradle.api.Plugin
import org.gradle.api.Project

class ComposeLibraryConventionPlugin : Plugin<Project> {

    override fun apply(target: Project) = with(target) {
        with(pluginManager) {
            apply(libs.plugins.multiplatform)
            apply(libs.plugins.android.library)
            apply(libs.plugins.compose.library)
            apply(libs.plugins.compose.compiler)
        }

        configureMultiplatformTargets()

        configureAndroidLibrary(libs = libs)
    }
}
