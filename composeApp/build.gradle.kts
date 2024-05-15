import org.jetbrains.compose.desktop.application.dsl.TargetFormat

plugins {
    id("jobbsyy.application")
}

kotlin {
    sourceSets {
        commonMain.dependencies {
            implementation(projects.core.arch)
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

        nativeDistributions {
            targetFormats(TargetFormat.Dmg, TargetFormat.Msi, TargetFormat.Deb)
            packageName = "com.archipelago.jobbsyy.desktopApp"
            packageVersion = "1.0.0"
        }
    }
}

buildConfig {
    // BuildConfig configuration here.
    // https://github.com/gmazzo/gradle-buildconfig-plugin#usage-in-kts
}
