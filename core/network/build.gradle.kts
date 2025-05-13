plugins {
  id("konspekt.library")
}

dependencies {
  commonMainImplementation(project.dependencies.platform(libs.koin.bom))
  commonMainImplementation(libs.koin.core)
  commonMainImplementation(libs.kotlinx.coroutines.core)
  commonMainImplementation(libs.kotlinx.serialization.json)

  androidMainImplementation(projects.core.resources)
  androidMainImplementation(projects.core.logging)

  androidMainImplementation(libs.androidx.core)
  androidMainImplementation(rootProject.files("libs/outline.aar"))
}

