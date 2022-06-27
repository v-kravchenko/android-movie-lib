plugins {
    id(BuildPlugins.ANDROID_LIB)
    id(BuildPlugins.KTFMT)
    id(BuildPlugins.PARCELIZE)
    kotlin("android")
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

    coroutines()

    implementation(Dependencies.JAVAX_INJECT)
    implementation(Dependencies.LIFECYCLE_LIVEDATA)
}

ktfmt {
    kotlinLangStyle()
}