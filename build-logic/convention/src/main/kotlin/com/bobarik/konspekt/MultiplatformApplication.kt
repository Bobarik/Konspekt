package com.bobarik.konspekt

import com.android.build.api.dsl.ApplicationExtension
import com.bobarik.konspekt.utils.applicationId
import com.bobarik.konspekt.utils.compileSdk
import com.bobarik.konspekt.utils.libs
import com.bobarik.konspekt.utils.minSdk
import com.bobarik.konspekt.utils.targetSdk
import com.bobarik.konspekt.utils.versionCode
import com.bobarik.konspekt.utils.versionName
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.get
import org.gradle.kotlin.dsl.getByType
import org.jetbrains.compose.ComposeExtension
import org.jetbrains.compose.desktop.DesktopExtension
import org.jetbrains.compose.desktop.application.dsl.TargetFormat
import org.jetbrains.kotlin.gradle.dsl.KotlinMultiplatformExtension

fun Project.configureMultiplatformApplication() = extensions.configure<KotlinMultiplatformExtension> {
  applyPlatformTargets(libs)
  val composeExtension = project.extensions.getByType<ComposeExtension>()
  val desktopExtension = composeExtension.extensions.getByType<DesktopExtension>()
  val compose = composeExtension.dependencies

  compilerOptions {
    freeCompilerArgs.add("-Xwhen-guards")
    freeCompilerArgs.add("-Xcontext-receivers")
  }

  sourceSets.apply {
    commonMain.dependencies {
      implementation(libs.napier)

      implementation(libs.kotlinx.coroutines.core)

      implementation(libs.ktor.core)

      implementation(project.dependencies.platform(libs.koin.bom))
      implementation(libs.koin.core)
      implementation(libs.koin.compose)

      implementation(libs.androidx.navigation)

      implementation(compose.runtime)
      implementation(compose.ui)
      implementation(compose.components.resources)
    }

    commonTest.dependencies {
      implementation(kotlin("test"))
    }

    jvmMain.dependencies {
      implementation(compose.desktop.common)
      implementation(compose.desktop.currentOs)
    }

    androidMain.dependencies {
      implementation(libs.androidx.appcompat)
      implementation(libs.androidx.activityCompose)
      implementation(libs.compose.uitooling)
      implementation(libs.kotlinx.coroutines.android)
      implementation(libs.koin.android)
    }
  }

  desktopExtension.application {
    mainClass = "MainKt"

    buildTypes.release.proguard {
      configurationFiles.from(project.file("proguard-rules.pro"))
      obfuscate.set(true)
    }

    nativeDistributions {
      targetFormats(TargetFormat.Dmg, TargetFormat.Msi, TargetFormat.Deb)
      packageName = libs.applicationId
      packageVersion = libs.versionName
    }
  }
}

fun Project.configureAndroidApplication() = extensions.configure<ApplicationExtension> {
  namespace = libs.applicationId
  compileSdk = libs.compileSdk

  defaultConfig {
    minSdk = libs.minSdk
    targetSdk = libs.targetSdk

    applicationId = libs.applicationId

    versionCode = libs.versionCode
    versionName = libs.versionName
  }

  sourceSets["main"].apply {
    manifest.srcFile("src/androidMain/AndroidManifest.xml")
    res.srcDirs("src/androidMain/resources")
  }

  buildTypes {
    release {
      isMinifyEnabled = true
      isShrinkResources = true

      signingConfig = signingConfigs.getByName("debug")

      proguardFiles(
        getDefaultProguardFile("proguard-android-optimize.txt"),
        "proguard-rules.pro",
      )
    }
    debug {
      isMinifyEnabled = false
      isShrinkResources = false

      proguardFiles(
        getDefaultProguardFile("proguard-android-optimize.txt"),
        "proguard-rules.pro",
      )
    }
  }
}
