object Versions {
    const val gradleVersion = "7.2.1"
    const val kotlinVersion = "1.7.0"
    const val hiltVersion = "2.42"
    const val roomVersion = "2.4.2"
    const val kspVersion = "1.7.0-1.0.6"
    const val coroutines = "1.6.3"
    const val gradleVersions = "0.41.0"

    const val coreKtx = "1.8.0"
    const val appCompat = "1.4.2"
    const val material = "1.6.1"
    const val constraint = "2.1.4"
    const val lifecycle = "2.4.1"
    const val navigation = "2.4.2"
    const val timber = "5.0.1"
    const val leakCanary = "2.9.1"
    const val retrofit2 = "2.9.0"
    const val paging = "3.1.1"
    const val dataStore = "1.0.0"
    const val coil = "2.1.0"
}

object BuildPlugins {
    val gradle by lazy {
        "com.android.application:com.android.application.gradle.plugin:${Versions.gradleVersion}"
    }
    val kotlin by lazy { "org.jetbrains.kotlin:kotlin-gradle-plugin:${Versions.kotlinVersion}" }
    val hilt by lazy { "com.google.dagger:hilt-android-gradle-plugin:${Versions.hiltVersion}" }
    val ksp by lazy {
        "com.google.devtools.ksp:com.google.devtools.ksp.gradle.plugin:${Versions.kspVersion}"
    }
    val safeArgs by lazy {
        "androidx.navigation:navigation-safe-args-gradle-plugin:${Versions.navigation}"
    }
    val gradleVersion by lazy {
        "com.github.ben-manes:gradle-versions-plugin:${Versions.gradleVersions}"
    }
}

object AppConfig {
    const val id = "com.redcatgames.movies"
    const val compileSdkVersion = 32
    const val buildToolsVersion = "32.0.0"
    const val minSdkVersion = 22
    const val targetSdkVersion = compileSdkVersion
    const val versionCode = 1
    const val versionName = "1.01"
}

object Dependencies {

    object Hilt {
        val runtime by lazy { "com.google.dagger:hilt-android:${Versions.hiltVersion}" }
        val compiler by lazy { "com.google.dagger:hilt-android-compiler:${Versions.hiltVersion}" }
    }

    object Coroutines {
        val core by lazy { "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.coroutines}" }
        val android by lazy {
            "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Versions.coroutines}"
        }
    }

    object Room {
        val runtime by lazy { "androidx.room:room-runtime:${Versions.roomVersion}" }
        val compiler by lazy { "androidx.room:room-compiler:${Versions.roomVersion}" }
        val coroutines by lazy { "androidx.room:room-ktx:${Versions.roomVersion}" }
    }

    object Lifecycle {
        val viewModel by lazy { "androidx.lifecycle:lifecycle-viewmodel-ktx:${Versions.lifecycle}" }
        val liveData by lazy { "androidx.lifecycle:lifecycle-livedata-ktx:${Versions.lifecycle}" }
        val saveState by lazy {
            "androidx.lifecycle:lifecycle-viewmodel-savedstate:${Versions.lifecycle}"
        }
    }

    object Navigation {
        val fragment by lazy {
            "androidx.navigation:navigation-fragment-ktx:${Versions.navigation}"
        }
        val ui by lazy { "androidx.navigation:navigation-ui-ktx:${Versions.navigation}" }
    }

    object Retrofit2 {
        val retrofit by lazy { "com.squareup.retrofit2:retrofit:${Versions.retrofit2}" }
        val gson by lazy { "com.squareup.retrofit2:converter-gson:${Versions.retrofit2}" }
    }

    val leakCanary by lazy { "com.squareup.leakcanary:leakcanary-android:${Versions.leakCanary}" }
    val coreKtx by lazy { "androidx.core:core-ktx:${Versions.coreKtx}" }
    val appCompat by lazy { "androidx.appcompat:appcompat:${Versions.appCompat}" }
    val material by lazy { "com.google.android.material:material:${Versions.material}" }
    val constraint by lazy { "androidx.constraintlayout:constraintlayout:${Versions.constraint}" }
    val timber by lazy { "com.jakewharton.timber:timber:${Versions.timber}" }
    val paging by lazy { "androidx.paging:paging-runtime:${Versions.paging}" }
    val dataStore by lazy { "androidx.datastore:datastore-preferences:${Versions.dataStore}" }
    val coil by lazy { "io.coil-kt:coil:${Versions.coil}" }
}
