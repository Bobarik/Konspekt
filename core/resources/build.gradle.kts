plugins {
  id("konspekt.library")
  alias(libs.plugins.compose.library)
  alias(libs.plugins.compose.compiler)
}

dependencies {
  commonMainImplementation(compose.runtime)
  commonMainImplementation(compose.components.resources)
}

compose.resources {
  publicResClass = true
  packageOfResClass = libs.versions.appId.get()
  generateResClass = auto
}
