plugins {
  `kotlin-dsl`
}

dependencies {
  compileOnly(libs.android.gradle.plugin)
  compileOnly(libs.compose.gradle.plugin)
  compileOnly(libs.compose.gradle.plugin.library)
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
      id = "konspekt.application"
      implementationClass = "plugins.ApplicationConventionPlugin"
    }
    register("feature") {
      id = "konspekt.feature"
      implementationClass = "plugins.FeatureConventionPlugin"
    }
    register("library") {
      id = "konspekt.library"
      implementationClass = "plugins.LibraryConventionPlugin"
    }
    register("commonLibrary") {
      id = "konspekt.library.common"
      implementationClass = "plugins.CommonLibraryConventionPlugin"
    }
  }
}
