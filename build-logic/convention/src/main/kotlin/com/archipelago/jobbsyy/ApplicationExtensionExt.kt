package com.archipelago.jobbsyy

import Config
import Config.ApplicationId
import Config.JavaVersion
import Config.MinSdk
import Config.TargetSdk
import Config.VersionCode
import Config.VersionName
import com.android.build.api.dsl.ApplicationExtension
import org.gradle.kotlin.dsl.get

fun ApplicationExtension.configureAndroidApplication() {
    namespace = ApplicationId
    compileSdk = Config.CompileSdk

    defaultConfig {
        minSdk = MinSdk
        targetSdk = TargetSdk

        applicationId = ApplicationId

        versionCode = VersionCode
        versionName = VersionName
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

    compileOptions {
        sourceCompatibility = JavaVersion
        targetCompatibility = JavaVersion
    }
}
