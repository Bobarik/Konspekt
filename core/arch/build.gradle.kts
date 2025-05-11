plugins {
  id("konspekt.library")

  alias(libs.plugins.compose.library)
  alias(libs.plugins.compose.compiler)
}

dependencies {
  commonMainImplementation(compose.runtime)

  commonMainImplementation(libs.androidx.lifecycle.compose)
  commonMainImplementation(libs.androidx.lifecycle.viewmodel)

  commonMainApi(libs.orbit.core)
  commonMainImplementation(libs.napier)
  commonMainImplementation(libs.kotlinx.coroutines.core)

  commonMainImplementation(platform(libs.koin.bom))
  commonMainImplementation(libs.koin.core)

  jvmMainImplementation(libs.kotlinx.coroutines.swing)
}
