plugins {
    `kotlin-dsl`
}

dependencies {
    compileOnly(libs.android.gradle.plugin)
    compileOnly(libs.kotlin.gradle.plugin)
    compileOnly(libs.ksp.gradle.plugin)
    compileOnly(files(libs.javaClass.superclass.protectionDomain.codeSource.location))
}

tasks {
    validatePlugins {
        enableStricterValidation = true
        failOnWarning = true
    }
}

gradlePlugin {
    plugins {
        register("application") {
            id = "korgy.application"
            implementationClass = "plugins.ApplicationConventionPlugin"
        }
        register("feature") {
            id = "korgy.feature"
            implementationClass = "plugins.FeatureConventionPlugin"
        }
        register("library") {
            id = "korgy.library"
            implementationClass = "plugins.LibraryConventionPlugin"
        }
        register("composeLibrary") {
            id = "korgy.library.compose"
            implementationClass = "plugins.ComposeLibraryConventionPlugin"
        }
    }
}
