plugins {
  id("konspekt.feature")
}

dependencies {
  commonMainImplementation(projects.feature.login)
  commonMainImplementation(projects.feature.home)
  commonMainImplementation(compose.runtime)
  commonMainImplementation(compose.material3)
  commonMainImplementation(compose.materialIconsExtended)
  commonMainImplementation(compose.components.resources)
}
