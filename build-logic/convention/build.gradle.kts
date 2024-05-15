import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    `kotlin-dsl`
}

group = "com.archipelago.jobbsyy"

java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
}

tasks.withType<KotlinCompile>().configureEach {
    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_17.toString()
    }
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
            id = "jobbsyy.application"
            implementationClass = "plugins.ApplicationConventionPlugin"
        }
        register("feature") {
            id = "jobbsyy.feature"
            implementationClass = "plugins.FeatureConventionPlugin"
        }
        register("library") {
            id = "jobbsyy.library"
            implementationClass = "plugins.LibraryConventionPlugin"
        }
        register("composeLibrary") {
            id = "jobbsyy.library.compose"
            implementationClass = "plugins.ComposeLibraryConventionPlugin"
        }
    }
}
