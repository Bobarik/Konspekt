plugins {
    alias(libs.plugins.multiplatform)
    alias(libs.plugins.compose.library)
    alias(libs.plugins.compose.compiler)
    alias(libs.plugins.buildConfig)
    alias(libs.plugins.kotlinx.serialization)
}

kotlin {
    jvm()

    sourceSets {
        commonMain.dependencies {
            implementation(compose.runtime)

            implementation(libs.essenty.lifecycle)
            implementation(libs.essenty.lifecycle.coroutines)

            implementation(libs.decompose)
            implementation(libs.decompose.compose)

            api(libs.orbit.core)
            implementation(libs.napier)
            implementation(libs.kotlinx.coroutines.core)

            implementation(project.dependencies.platform(libs.koin.bom))
            implementation(libs.koin.core)
        }

        jvmMain.dependencies {
            implementation(libs.kotlinx.coroutines.swing)
        }
    }
}
