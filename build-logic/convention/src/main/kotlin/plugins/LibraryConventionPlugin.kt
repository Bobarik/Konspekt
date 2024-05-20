package plugins

import com.bobarik.korgy.configureAndroidLibrary
import com.bobarik.korgy.configureMultiplatformTargets
import com.bobarik.korgy.libs
import org.gradle.api.Plugin
import org.gradle.api.Project

class LibraryConventionPlugin : Plugin<Project> {

    override fun apply(target: Project) = with(target) {
        with(pluginManager) {
            apply(libs.plugins.multiplatform.get().pluginId)
            apply(libs.plugins.android.library.get().pluginId)
        }

        configureMultiplatformTargets()

        configureAndroidLibrary()
    }
}
