package com.bobarik.konspekt

import com.android.build.gradle.LibraryExtension
import com.bobarik.konspekt.utils.applicationId
import com.bobarik.konspekt.utils.compileSdk
import com.bobarik.konspekt.utils.libs
import org.gradle.accessors.dm.LibrariesForLibs
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import org.jetbrains.kotlin.gradle.dsl.KotlinMultiplatformExtension

fun Project.configureAndroidLibrary(libs: LibrariesForLibs) = extensions.configure<LibraryExtension> {
    compileSdk = libs.compileSdk

    val moduleNamespace = "${libs.applicationId}.${path.split(':')[2]}"
    namespace = moduleNamespace
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
