// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.jetbrains.kotlin.android) apply false
    id("com.google.gms.google-services") version "4.4.2" apply false
}
buildscript {
    extra.apply {
        set("kotlin_version", "1.8.0")
        set("retrofit_version", "2.9.0")
        set("room_version", "2.5.0")
    }
    dependencies {
        classpath ("com.google.gms:google-services:4.3.15")
        classpath("com.android.tools.build:gradle:8.1.1")
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:${project.extra["kotlin_version"]}")
    }
}

