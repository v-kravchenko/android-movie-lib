plugins {
    id("com.android.library")
    kotlin("android")
    kotlin("kapt")
    id("dagger.hilt.android.plugin")
    id("kotlin-parcelize")
    id("androidx.navigation.safeargs.kotlin")
    id("com.ncorti.ktfmt.gradle")
}

android {
    compileSdk = AppConfig.compileSdkVersion

    defaultConfig {
        minSdk = AppConfig.minSdkVersion
        targetSdk = AppConfig.targetSdkVersion
        consumerProguardFiles("consumer-rules.pro")
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    
    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_1_8.toString()
    }

    buildFeatures { viewBinding = true }
}

dependencies {
    implementation(project(":util"))
    implementation(project(":domain"))

    // Hilt
    implementation(Dependencies.Hilt.runtime)
    kapt(Dependencies.Hilt.compiler)

    // Coroutines
    implementation(Dependencies.Coroutines.core)
    implementation(Dependencies.Coroutines.android)

    // Lifecycle
    implementation(Dependencies.Lifecycle.viewModel)
    implementation(Dependencies.Lifecycle.liveData)
    implementation(Dependencies.Lifecycle.saveState)

    // Navigation
    implementation(Dependencies.Navigation.fragment)
    implementation(Dependencies.Navigation.ui)

    // Coil
    implementation(Dependencies.coil)

    // Other
    implementation(Dependencies.appCompat)
    implementation(Dependencies.material)
    implementation(Dependencies.constraint)
    implementation(Dependencies.timber)
}

ktfmt {
    kotlinLangStyle()
}