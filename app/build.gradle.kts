plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    kotlin("kapt")
    id("dagger.hilt.android.plugin")
}

android {
    compileSdk = AppConfig.compileSdk

    defaultConfig {
        applicationId = AppConfig.applicationId
        minSdk = AppConfig.minSdk
        targetSdk = AppConfig.targetSdk
        versionCode = AppConfig.versionCode
        versionName = AppConfig.versionName

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        val release by getting {
            isMinifyEnabled = true
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
        val debug by getting {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        viewBinding = true
    }
}

dependencies {

    implementation ("androidx.core:core-ktx:${Versions.androidxCoreKts}")
    implementation ("androidx.appcompat:appcompat:${Versions.androidxAppCompat}")
    implementation ("com.google.android.material:material:${Versions.googleMaterial}")
    implementation ("androidx.constraintlayout:constraintlayout:${Versions.androidxConstraintLayout}")

    // Hilt
    implementation("com.google.dagger:hilt-android:${Versions.googleHilt}")
    kapt("com.google.dagger:hilt-android-compiler:${Versions.googleHilt}")

    // Navigation
    implementation("androidx.navigation:navigation-fragment-ktx:${Versions.androidXNavigation}")
    implementation("androidx.navigation:navigation-ui-ktx:${Versions.androidXNavigation}")
}