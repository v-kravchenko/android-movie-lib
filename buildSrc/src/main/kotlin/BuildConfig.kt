object Versions {
    const val gradleVersion = "7.1.3"
    const val kotlinVersion = "1.6.20"
    const val hiltVersion = "2.38.1"

    const val coreKtx = "1.7.0"
    const val appCompat = "1.4.1"
    const val material = "1.5.0"
    const val constraint = "2.1.3"
}

object BuildPlugins {
    val gradle by lazy { "com.android.application:com.android.application.gradle.plugin:${Versions.gradleVersion}" }
    val kotlin by lazy { "org.jetbrains.kotlin:kotlin-gradle-plugin:${Versions.kotlinVersion}" }
    val hilt by lazy { "com.google.dagger:hilt-android-gradle-plugin:${Versions.hiltVersion}" }
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
    val hilt by lazy { "com.google.dagger:hilt-android:${Versions.hiltVersion}" }
    val hiltCompiler by lazy { "com.google.dagger:hilt-android-compiler:${Versions.hiltVersion}" }
    val coreKtx by lazy {"androidx.core:core-ktx:${Versions.coreKtx}" }
    val appCompat by lazy { "androidx.appcompat:appcompat:${Versions.appCompat}"}
    val material by lazy { "com.google.android.material:material:${Versions.material}"}
    val constraint by lazy { "androidx.constraintlayout:constraintlayout:${Versions.constraint}" }
}