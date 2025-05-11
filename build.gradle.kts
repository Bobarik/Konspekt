import io.gitlab.arturbosch.detekt.Detekt

plugins {
  alias(libs.plugins.multiplatform) apply false
  alias(libs.plugins.compose.library) apply false
  alias(libs.plugins.compose.compiler) apply false
  alias(libs.plugins.android.application) apply false
  alias(libs.plugins.buildConfig) apply false
  alias(libs.plugins.kotlinx.serialization) apply false
  alias(libs.plugins.ksp) apply false
  alias(libs.plugins.room) apply false
  alias(libs.plugins.detekt) apply true
}

dependencies {
  detektPlugins(libs.detekt.formatting)
}

detekt {
  allRules = false
  autoCorrect = true
  ignoreFailures = false
  buildUponDefaultConfig = true
  config.from(rootProject.file("config/detekt.yml"))
}

tasks.withType<Detekt>().configureEach {
  reports {
    html.required = true
    html.outputLocation = rootProject.file("build/reports/detekt/detekt.html")
    xml.required = true
    xml.outputLocation = rootProject.file("build/reports/detekt/detekt.xml")
    sarif.required = true
  }
}
