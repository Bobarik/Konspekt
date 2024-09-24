plugins {
    alias(libs.plugins.multiplatform)
    alias(libs.plugins.compose.library)
    alias(libs.plugins.compose.compiler)
}

kotlin {
    jvm()

    sourceSets {
        commonMain {
            dependencies {
                implementation(compose.runtime)
                implementation(libs.navigation.compose)
            }
        }
    }
}
