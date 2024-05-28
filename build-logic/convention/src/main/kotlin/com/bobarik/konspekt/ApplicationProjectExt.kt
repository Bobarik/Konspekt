package com.bobarik.konspekt

import com.android.build.api.dsl.ApplicationExtension
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
                "proguard-rules.pro"
            )
        }
        debug {
            isMinifyEnabled = false

            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
}
