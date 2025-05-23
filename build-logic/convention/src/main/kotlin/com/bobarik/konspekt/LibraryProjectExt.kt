package com.bobarik.konspekt

import com.android.build.gradle.LibraryExtension
import com.bobarik.konspekt.utils.applicationId
import com.bobarik.konspekt.utils.compileSdk
import com.bobarik.konspekt.utils.javaVersion
import com.bobarik.konspekt.utils.libs
import org.gradle.accessors.dm.LibrariesForLibs
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import org.jetbrains.kotlin.gradle.dsl.KotlinMultiplatformExtension

fun Project.configureAndroidLibrary() = extensions.configure<LibraryExtension> {
  compileSdk = libs.compileSdk

  val moduleNamespace = "${libs.applicationId}${path.replace(':', '.')}"
  namespace = moduleNamespace
}

fun Project.configureMultiplatformTargets() = extensions.configure<KotlinMultiplatformExtension> {
  compilerOptions {
    freeCompilerArgs.add("-Xwhen-guards")
    freeCompilerArgs.add("-Xcontext-receivers")
  }

  applyPlatformTargets(libs)
}

fun Project.configureCommonMultiplatformTargets() = extensions.configure<KotlinMultiplatformExtension> {
  compilerOptions {
    freeCompilerArgs.add("-Xwhen-guards")
    freeCompilerArgs.add("-Xcontext-receivers")
  }

  applyCommonPlatformTargets(libs)
}

fun KotlinMultiplatformExtension.applyPlatformTargets(
  libs: LibrariesForLibs,
) {
  androidTarget()
  jvm()
  applyDefaultHierarchyTemplate()

  jvmToolchain(libs.javaVersion)
}

fun KotlinMultiplatformExtension.applyCommonPlatformTargets(
  libs: LibrariesForLibs,
) {
  jvm()
  applyDefaultHierarchyTemplate()

  jvmToolchain(libs.javaVersion)
}
