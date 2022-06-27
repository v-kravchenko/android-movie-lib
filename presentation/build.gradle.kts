plugins {
    id(BuildPlugins.ANDROID_LIB)
    kotlin("android")
    kotlin("kapt")
    id(BuildPlugins.HILT)
    id(BuildPlugins.KTFMT)
    id(BuildPlugins.PARCELIZE)
    id(BuildPlugins.NAVIGATION_SAFEARGS)
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
    hilt()
    coroutines()
    lifecycle()
    navigation()

    implementation(Dependencies.COIL)
    implementation(Dependencies.ANDROID_MATERIAL)
    implementation(Dependencies.CONSTRAINT_LAYOUT)
    implementation(Dependencies.TIMBER)
}

ktfmt {
    kotlinLangStyle()
}