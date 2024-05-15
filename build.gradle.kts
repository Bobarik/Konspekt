plugins {
    alias(libs.plugins.multiplatform) apply false
    alias(libs.plugins.compose.library) apply  false
    alias(libs.plugins.compose.compiler) apply  false
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.buildConfig) apply false
    alias(libs.plugins.kotlinx.serialization) apply false
}
