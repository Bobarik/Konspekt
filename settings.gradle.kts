rootProject.name = "Konspekt"
include(":composeApp")

pluginManagement {
  includeBuild("build-logic")
  repositories {
    google()
    gradlePluginPortal()
    mavenCentral()
    maven("https://maven.pkg.jetbrains.space/public/p/compose/dev")
  }
}

dependencyResolutionManagement {
  @Suppress("UnstableApiUsage")
  repositories {
    google()
    mavenCentral()
    maven("https://maven.pkg.jetbrains.space/public/p/compose/dev")
  }
}

enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")

include(":core:arch")
include(":core:domain")
include(":core:database")
include(":core:design_system")
include(":core:logging")
include(":core:navigation")
include(":core:network")
include(":core:resources")
include(":feature:login:api")
include(":feature:login:impl")
include(":feature:home:api")
include(":feature:home:impl")
