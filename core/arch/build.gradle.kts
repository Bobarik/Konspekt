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
            implementation(compose.ui)

            api(libs.orbit.core)
            implementation(libs.napier)
            implementation(libs.kotlinx.coroutines.core)

            implementation(project.dependencies.platform(libs.koin.bom))
            implementation(libs.koin.core)

            implementation(libs.androidx.lifecycle.viewmodel)
            implementation(libs.androidx.lifecycle.viewmodel.compose)
            implementation(libs.androidx.lifecycle.runtime.compose)
        }

        jvmMain.dependencies {
            implementation(libs.kotlinx.coroutines.swing)
        }
    }
}
