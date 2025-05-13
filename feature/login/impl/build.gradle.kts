plugins {
  id("konspekt.feature")
}

dependencies {
  commonMainImplementation(projects.feature.login.api)
  commonMainImplementation(projects.feature.home.api)
  androidMainImplementation(projects.core.network)

  commonMainImplementation(compose.runtime)
  commonMainImplementation(compose.material3)
  commonMainImplementation(compose.materialIconsExtended)
  commonMainImplementation(compose.components.resources)
}
