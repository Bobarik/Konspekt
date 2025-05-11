plugins {
  id("konspekt.application")
}

dependencies {
  commonMainImplementation(projects.core.arch)
  commonMainImplementation(projects.core.database)
  commonMainImplementation(projects.core.designSystem)
  commonMainImplementation(projects.core.navigation)
  commonMainImplementation(projects.feature.login.api)
  commonMainImplementation(projects.feature.login.impl)
  commonMainImplementation(projects.feature.home.api)
  commonMainImplementation(projects.feature.home.impl)
}

buildConfig {
  // BuildConfig configuration here.
  // https://github.com/gmazzo/gradle-buildconfig-plugin#usage-in-kts
}
