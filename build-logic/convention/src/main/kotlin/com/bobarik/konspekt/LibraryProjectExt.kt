package com.bobarik.konspekt

import org.gradle.api.Project
import com.android.build.gradle.LibraryExtension
import org.gradle.accessors.dm.LibrariesForLibs
import org.gradle.kotlin.dsl.configure
import org.jetbrains.kotlin.gradle.dsl.KotlinMultiplatformExtension

fun Project.configureAndroidLibrary(libs: LibrariesForLibs) = extensions.configure<LibraryExtension> {
    compileSdk = libs.compileSdk

    val featureNamespace = "${libs.applicationId}.${path.split(':')[1]}"
    namespace = featureNamespace
}

fun Project.configureMultiplatformTargets() = extensions.configure<KotlinMultiplatformExtension> {
    applyPlatformTargets(libs)
}

fun KotlinMultiplatformExtension.applyPlatformTargets(
    libs: LibrariesForLibs
) {
    androidTarget()
    jvm()
    applyDefaultHierarchyTemplate()

    jvmToolchain(libs.versions.java.get().toInt())
}
