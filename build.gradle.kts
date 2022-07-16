// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    id("com.android.application") version Versions.gradlePluginAndroid apply false
    id("com.android.library") version Versions.gradlePluginAndroid apply false
    id("org.jetbrains.kotlin.android") version Versions.gradlePluginKotlin apply false
    id("com.google.dagger.hilt.android") version Versions.gradlePluginHilt apply false
}