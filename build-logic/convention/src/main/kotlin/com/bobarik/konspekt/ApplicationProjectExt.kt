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

      signingConfig = signingConfigs.getByName("debug")

      proguardFiles(
        getDefaultProguardFile("proguard-android-optimize.txt"),
        "proguard-rules.pro",
      )
    }
    debug {
      isMinifyEnabled = false

      proguardFiles(
        getDefaultProguardFile("proguard-android-optimize.txt"),
        "proguard-rules.pro",
      )
    }
  }
}
