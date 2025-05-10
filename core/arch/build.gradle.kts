plugins {
  id("konspekt.library")

  alias(libs.plugins.compose.library)
  alias(libs.plugins.compose.compiler)
}

dependencies {
  commonMainImplementation(compose.runtime)

  commonMainImplementation(libs.essenty.lifecycle)
  commonMainImplementation(libs.essenty.lifecycle.coroutines)

  commonMainImplementation(libs.decompose)
  commonMainImplementation(libs.decompose.compose)

  commonMainApi(libs.orbit.core)
  commonMainImplementation(libs.napier)
  commonMainImplementation(libs.kotlinx.coroutines.core)

  commonMainImplementation(platform(libs.koin.bom))
  commonMainImplementation(libs.koin.core)

  jvmMainImplementation(libs.kotlinx.coroutines.swing)
}
