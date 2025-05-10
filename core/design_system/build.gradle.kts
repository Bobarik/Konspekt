plugins {
  id("konspekt.library")
  alias(libs.plugins.compose.library)
  alias(libs.plugins.compose.compiler)
}

dependencies {
  commonMainImplementation(compose.runtime)
  commonMainImplementation(compose.material3)
  commonMainImplementation(compose.components.resources)
}
