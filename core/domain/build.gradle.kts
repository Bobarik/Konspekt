plugins {
  id("konspekt.library.common")
}

dependencies {
  commonMainImplementation(project.dependencies.platform(libs.koin.bom))
  commonMainImplementation(libs.koin.core)
  commonMainImplementation(libs.kotlinx.coroutines.core)
}
