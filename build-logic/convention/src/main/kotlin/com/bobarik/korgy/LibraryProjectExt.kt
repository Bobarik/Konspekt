package com.bobarik.korgy

import org.gradle.api.Project
import Config.ApplicationId
import Config.CompileSdk
import com.android.build.gradle.LibraryExtension
import org.gradle.kotlin.dsl.configure
import org.jetbrains.kotlin.gradle.dsl.KotlinMultiplatformExtension

fun Project.configureAndroidLibrary() = extensions.configure<LibraryExtension> {
    compileSdk = CompileSdk

    val featureNamespace = "$ApplicationId.${path.split(':')[1]}"
    namespace = featureNamespace
}

fun Project.configureMultiplatformTargets() = extensions.configure<KotlinMultiplatformExtension> {
    jvm()
    androidTarget()
    applyDefaultHierarchyTemplate()

    jvmToolchain(libs.versions.java.get().toInt())
}
