plugins {
    id("korgy.feature")
}

kotlin {
    sourceSets {
        commonMain.dependencies {
            implementation(projects.feature.login)
            implementation(projects.feature.home)

            implementation(compose.runtime)
            implementation(compose.material3)
            implementation(compose.materialIconsExtended)
            implementation(compose.components.resources)
        }
    }
}
