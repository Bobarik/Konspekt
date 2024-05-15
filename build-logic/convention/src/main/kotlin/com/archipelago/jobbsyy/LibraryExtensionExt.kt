package com.archipelago.jobbsyy

import Config.CompileSdk
import Config.MinSdk
import com.android.build.gradle.LibraryExtension

fun LibraryExtension.configureAndroidLibrary() {
    compileSdk = CompileSdk

    defaultConfig.minSdk = MinSdk

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
