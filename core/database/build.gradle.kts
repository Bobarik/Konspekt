plugins {
  id("konspekt.library")
  alias(libs.plugins.ksp)
  alias(libs.plugins.room)
}

dependencies {
  commonMainImplementation(projects.core.domain)
  commonMainImplementation(libs.sqlite.bundled)
  commonMainImplementation(libs.room.runtime)
  commonMainImplementation(project.dependencies.platform(libs.koin.bom))
  commonMainImplementation(libs.koin.core)

  androidMainImplementation(libs.koin.android)
}

room {
  schemaDirectory("$projectDir/schemas")
}

dependencies {
  kspAndroid(libs.room.compiler)
  kspJvm(libs.room.compiler)
}
