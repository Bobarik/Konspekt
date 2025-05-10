plugins {
  id("konspekt.application")
}

dependencies {
  commonMainImplementation(projects.core.arch)
  commonMainImplementation(projects.core.database)
  commonMainImplementation(projects.core.designSystem)
  commonMainImplementation(projects.feature.root)
  commonMainImplementation(projects.feature.login)
  commonMainImplementation(projects.feature.home)
}

buildConfig {
  // BuildConfig configuration here.
  // https://github.com/gmazzo/gradle-buildconfig-plugin#usage-in-kts
}
