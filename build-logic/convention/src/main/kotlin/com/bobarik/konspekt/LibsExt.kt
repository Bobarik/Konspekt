package com.bobarik.konspekt

import org.gradle.accessors.dm.LibrariesForLibs

val LibrariesForLibs.applicationId: String
    get() = versions.appId.get()

val LibrariesForLibs.versionCode: Int
    get() = versions.versionCode.get().toInt()

val LibrariesForLibs.versionName: String
    get() = versions.versionName.get()

val LibrariesForLibs.minSdk: Int
    get() = versions.minSdk.get().toInt()

val LibrariesForLibs.compileSdk: Int
    get() = versions.compileSdk.get().toInt()

val LibrariesForLibs.targetSdk: Int
    get() = versions.targetSdk.get().toInt()
