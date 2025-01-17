plugins {
    id("konspekt.library.compose")
}

kotlin {
    sourceSets {
        commonMain.dependencies {
            implementation(compose.runtime)
            implementation(compose.material3)
            implementation(compose.components.resources)
        }
    }
}
