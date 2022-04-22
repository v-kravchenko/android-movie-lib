object Versions {
    const val gradleVersion = "7.1.3"
    const val kotlinVersion = "1.6.20"
    const val hiltVersion = "2.38.1"
    const val roomVersion = "2.4.2"
    const val kspVersion = "1.6.20-1.0.5"

    const val coroutines = "1.6.1"
    const val coreKtx = "1.7.0"
    const val appCompat = "1.4.1"
    const val material = "1.5.0"
    const val constraint = "2.1.3"
    const val lifecycle = "2.4.1"
}

object BuildPlugins {
    val gradle by lazy { "com.android.application:com.android.application.gradle.plugin:${Versions.gradleVersion}" }
    val kotlin by lazy { "org.jetbrains.kotlin:kotlin-gradle-plugin:${Versions.kotlinVersion}" }
    val hilt by lazy { "com.google.dagger:hilt-android-gradle-plugin:${Versions.hiltVersion}" }
    val ksp by lazy { "com.google.devtools.ksp:com.google.devtools.ksp.gradle.plugin:${Versions.kspVersion}" }
}

object AppConfig {
    const val id = "com.redcatgames.musiclib"
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
        val android by lazy { "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Versions.coroutines}" }
    }

    object Room {
        val runtime by lazy { "androidx.room:room-runtime:${Versions.roomVersion}" }
        val compiler by lazy { "androidx.room:room-compiler:${Versions.roomVersion}" }
        val coroutines by lazy { "androidx.room:room-ktx:${Versions.roomVersion}" }
    }

    object Lifecycle {
        val viewModel by lazy { "androidx.lifecycle:lifecycle-viewmodel-ktx:${Versions.lifecycle}" }
        val liveData by lazy { "androidx.lifecycle:lifecycle-livedata-ktx:${Versions.lifecycle}" }
        val saveState by lazy { "androidx.lifecycle:lifecycle-viewmodel-savedstate:${Versions.lifecycle}" }
    }

    val coreKtx by lazy {"androidx.core:core-ktx:${Versions.coreKtx}" }
    val appCompat by lazy { "androidx.appcompat:appcompat:${Versions.appCompat}"}
    val material by lazy { "com.google.android.material:material:${Versions.material}"}
    val constraint by lazy { "androidx.constraintlayout:constraintlayout:${Versions.constraint}" }
}