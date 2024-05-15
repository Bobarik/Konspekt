package plugins

import com.android.build.api.dsl.ApplicationExtension
import com.archipelago.jobbsyy.configureAndroidApplication
import com.archipelago.jobbsyy.configureMultiplatformApplication
import com.archipelago.jobbsyy.libs
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import org.jetbrains.kotlin.gradle.dsl.KotlinMultiplatformExtension

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

        extensions.configure<KotlinMultiplatformExtension> {
            configureMultiplatformApplication(libs)
        }

        extensions.configure<ApplicationExtension> {
            configureAndroidApplication()
        }
    }
}
