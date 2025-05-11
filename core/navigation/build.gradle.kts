plugins {
  id("konspekt.library.common")

  alias(libs.plugins.compose.library)
  alias(libs.plugins.compose.compiler)
}

dependencies {
  commonMainImplementation(compose.runtime)

  commonMainImplementation(libs.decompose)
  commonMainImplementation(libs.decompose.compose)

  commonMainImplementation(project.dependencies.platform(libs.koin.bom))
  commonMainImplementation(libs.koin.core)
}
