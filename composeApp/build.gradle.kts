import com.bobarik.konspekt.utils.applicationId
import com.bobarik.konspekt.utils.versionName
import org.jetbrains.compose.desktop.application.dsl.TargetFormat

plugins {
    id("konspekt.application")
}

kotlin {
    sourceSets {
        commonMain.dependencies {
            implementation(projects.core.arch)
            implementation(projects.core.database)
            implementation(projects.core.designSystem)
            implementation(projects.feature.root)
            implementation(projects.feature.login)
            implementation(projects.feature.home)

            implementation(compose.runtime)
            implementation(compose.ui)
            implementation(compose.components.resources)
        }

        jvmMain.dependencies {
            implementation(compose.desktop.common)
            implementation(compose.desktop.currentOs)
        }
    }
}

compose.desktop {
    application {
        mainClass = "MainKt"

        buildTypes.release.proguard {
            configurationFiles.from(project.file("proguard-rules.pro"))
            obfuscate = true
        }

        nativeDistributions {
            targetFormats(TargetFormat.Dmg, TargetFormat.Msi, TargetFormat.Deb)
            packageName = libs.applicationId
            packageVersion = libs.versionName
        }
    }
}

buildConfig {
    // BuildConfig configuration here.
    // https://github.com/gmazzo/gradle-buildconfig-plugin#usage-in-kts
}
