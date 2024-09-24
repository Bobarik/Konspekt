plugins {
    id("konspekt.feature")
}

kotlin.sourceSets.commonMain {
    dependencies {
        implementation(projects.feature.login.api)
        implementation(projects.feature.home.api)
    }
}
