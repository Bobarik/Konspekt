plugins {
  id("konspekt.library.common")
}

dependencies {
  commonMainImplementation(libs.decompose)
  commonMainImplementation(libs.decompose.compose)

  commonMainImplementation(project.dependencies.platform(libs.koin.bom))
  commonMainImplementation(libs.koin.core)
}
