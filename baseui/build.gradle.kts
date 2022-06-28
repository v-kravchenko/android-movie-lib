plugins {
    id(BuildPlugins.ANDROID_LIB)
    kotlin("android")
    kotlin("kapt")
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

    appCompat()

    implementation(Dependencies.ANDROID_MATERIAL)
}