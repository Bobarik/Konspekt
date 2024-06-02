package plugins

import com.bobarik.konspekt.utils.apply
import com.bobarik.konspekt.configureAndroidLibrary
import com.bobarik.konspekt.configureMultiplatformTargets
import com.bobarik.konspekt.utils.libs
import org.gradle.api.Plugin
import org.gradle.api.Project

class LibraryConventionPlugin : Plugin<Project> {

    override fun apply(target: Project) = with(target) {
        with(pluginManager) {
            apply(libs.plugins.multiplatform)
            apply(libs.plugins.android.library)
        }

        configureMultiplatformTargets()

        configureAndroidLibrary(libs = libs)
    }
}
