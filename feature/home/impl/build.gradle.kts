plugins {
  id("konspekt.feature")
}

dependencies {
  commonMainImplementation(projects.feature.home.api)

  commonMainImplementation(compose.runtime)
  commonMainImplementation(compose.material3)
  commonMainImplementation(compose.materialIconsExtended)
  commonMainImplementation(compose.components.resources)
}
