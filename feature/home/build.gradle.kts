plugins {
  id("konspekt.feature")
}

dependencies {
  commonMainImplementation(compose.runtime)
  commonMainImplementation(compose.material3)
  commonMainImplementation(compose.materialIconsExtended)
  commonMainImplementation(compose.components.resources)
}
