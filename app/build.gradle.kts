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

    flavorDimensions.add("env")
    productFlavors {
        create("prod") {
            dimension = "env"
            buildConfigField("String", "GITHUB_BASE_URL", "\"https://api.github.com\"")
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

    implementation("androidx.core:core-ktx:${Versions.androidxCoreKts}")
    implementation("androidx.appcompat:appcompat:${Versions.androidxAppCompat}")
    implementation("com.google.android.material:material:${Versions.googleMaterial}")
    implementation("androidx.constraintlayout:constraintlayout:${Versions.androidxConstraintLayout}")
    implementation("androidx.swiperefreshlayout:swiperefreshlayout:${Versions.androidxSwipeRefreshLayout}")

    implementation("com.google.code.gson:gson:${Versions.googleGson}")

    // Paging
    implementation("androidx.paging:paging-runtime:${Versions.androidxPaging}")

    // Hilt
    implementation("com.google.dagger:hilt-android:${Versions.googleHilt}")
    kapt("com.google.dagger:hilt-android-compiler:${Versions.googleHilt}")

    // Navigation
    implementation("androidx.navigation:navigation-fragment-ktx:${Versions.androidXNavigation}")
    implementation("androidx.navigation:navigation-ui-ktx:${Versions.androidXNavigation}")

    // Glide
    implementation ("com.github.bumptech.glide:glide:${Versions.bumptechGlide}")
    kapt("com.github.bumptech.glide:compiler:${Versions.bumptechGlide}")

    // Retrofit
    implementation("com.squareup.retrofit2:retrofit:${Versions.squareupRetrofit}")
    implementation("com.squareup.retrofit2:converter-gson:${Versions.squareupRetrofit}")
}