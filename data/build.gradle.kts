plugins {
    id(BuildPlugins.ANDROID_LIB)
    id(BuildPlugins.HILT)
    id(BuildPlugins.KSP)
    id(BuildPlugins.KTFMT)
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
}

dependencies {
    implementation(project(":util"))
    implementation(project(":domain"))

    hilt()
    room()
    retrofit()

    implementation(Dependencies.DATASTORE)
    implementation(Dependencies.LIFECYCLE_LIVEDATA)
}

ktfmt {
    kotlinLangStyle()
}