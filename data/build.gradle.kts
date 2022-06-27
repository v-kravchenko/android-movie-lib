plugins {
    id("com.android.library")
    kotlin("android")
    kotlin("kapt")
    id("com.google.devtools.ksp")
    id("dagger.hilt.android.plugin")
    id("com.ncorti.ktfmt.gradle")
}

android {
    compileSdk = AppConfig.compileSdkVersion

    defaultConfig {
        minSdk = AppConfig.minSdkVersion
        targetSdk = AppConfig.targetSdkVersion
    }

    buildTypes {
        release {
            isMinifyEnabled = true
            isMinifyEnabled = true
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
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

    implementation(Dependencies.coreKtx)

    // Lifecycle
    implementation(Dependencies.Lifecycle.liveData)

    // Hilt
    implementation(Dependencies.Hilt.runtime)
    kapt(Dependencies.Hilt.compiler)

    // Datastore
    implementation(Dependencies.dataStore)

    // Room
    implementation(Dependencies.Room.runtime)
    implementation(Dependencies.Room.coroutines)
    ksp(Dependencies.Room.compiler)

    // Retrofit2
    implementation(Dependencies.Retrofit2.retrofit)
    implementation(Dependencies.Retrofit2.gson)
}

ktfmt {
    kotlinLangStyle()
}