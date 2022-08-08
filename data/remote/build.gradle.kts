plugins {
    id(BuildPlugins.ANDROID_LIB)
    id(BuildPlugins.HILT)
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
    implementation(project(":data:preferences"))

    hilt()
    retrofit()
    coroutines()
    implementation("com.squareup.okhttp3:logging-interceptor:5.0.0-alpha.5")
}

ktfmt {
    kotlinLangStyle()
}