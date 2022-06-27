import org.gradle.api.artifacts.dsl.DependencyHandler

object AppConfig {
    const val id = "com.redcatgames.movies"
    const val compileSdkVersion = 32
    const val buildToolsVersion = "32.0.0"
    const val minSdkVersion = 22
    const val targetSdkVersion = compileSdkVersion
    const val versionCode = 1
    const val versionName = "1.01"
}

object Versions {
    const val gradleVersion = "7.2.1"
    const val kotlinVersion = "1.7.0"
    const val hiltVersion = "2.42"
    const val roomVersion = "2.4.2"
    const val kspVersion = "1.7.0-1.0.6"
    const val coroutines = "1.6.3"
    const val gradleVersions = "0.41.0"
    const val ktFmt = "0.8.0"

    const val coreKtx = "1.8.0"
    const val appCompat = "1.4.2"
    const val material = "1.6.1"
    const val constraint = "2.1.4"
    const val lifecycle = "2.4.1"
    const val navigation = "2.4.2"
    const val timber = "5.0.1"
    const val leakCanary = "2.9.1"
    const val retrofit2 = "2.9.0"
    const val dataStore = "1.0.0"
    const val coil = "2.1.0"
}

object BuildPlugins {
    const val gradle =
        "com.android.application:com.android.application.gradle.plugin:${Versions.gradleVersion}"
    const val kotlin = "org.jetbrains.kotlin:kotlin-gradle-plugin:${Versions.kotlinVersion}"
    const val hilt = "com.google.dagger:hilt-android-gradle-plugin:${Versions.hiltVersion}"
    const val ksp = "com.google.devtools.ksp:com.google.devtools.ksp.gradle.plugin:${Versions.kspVersion}"
    const val safeArgs = "androidx.navigation:navigation-safe-args-gradle-plugin:${Versions.navigation}"
    const val gradleVersion = "com.github.ben-manes:gradle-versions-plugin:${Versions.gradleVersions}"
    const val ktFmt = "com.ncorti.ktfmt.gradle:plugin:${Versions.ktFmt}"
}

object Dependencies {

    const val HILT_ANDROID = "com.google.dagger:hilt-android:${Versions.hiltVersion}"
    const val HILT_COMPILER = "com.google.dagger:hilt-android-compiler:${Versions.hiltVersion}"

    const val ANDROID_APP_COMPAT = "androidx.appcompat:appcompat:${Versions.appCompat}"
    const val ANDROID_CORE_KTX = "androidx.core:core-ktx:${Versions.coreKtx}"

    const val ANDROID_MATERIAL = "com.google.android.material:material:${Versions.material}"

    const val ROOM_RUNTIME = "androidx.room:room-runtime:${Versions.roomVersion}"
    const val ROOM_COMPILER = "androidx.room:room-compiler:${Versions.roomVersion}"
    const val ROOM_KTX = "androidx.room:room-ktx:${Versions.roomVersion}"

    const val LIFECYCLE_VIEWMODEL = "androidx.lifecycle:lifecycle-viewmodel-ktx:${Versions.lifecycle}"
    const val LIFECYCLE_LIVEDATA = "androidx.lifecycle:lifecycle-livedata-ktx:${Versions.lifecycle}"
    const val LIFECYCLE_SAVEDSTATE = "androidx.lifecycle:lifecycle-viewmodel-savedstate:${Versions.lifecycle}"

    const val COROUTINES_CORE = "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.coroutines}"
    const val COROUTINES_ANDROID = "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Versions.coroutines}"

    const val NAVIGATION_FRAGMENT = "androidx.navigation:navigation-fragment-ktx:${Versions.navigation}"
    const val NAVIGATION_UI = "androidx.navigation:navigation-ui-ktx:${Versions.navigation}"

    const val RETROFIT = "com.squareup.retrofit2:retrofit:${Versions.retrofit2}"
    const val RETROFIT_GSON = "com.squareup.retrofit2:converter-gson:${Versions.retrofit2}"

    const val LEAKCANARY = "com.squareup.leakcanary:leakcanary-android:${Versions.leakCanary}"
    const val CONSTRAINT_LAYOUT = "androidx.constraintlayout:constraintlayout:${Versions.constraint}"
    const val TIMBER = "com.jakewharton.timber:timber:${Versions.timber}"
    const val DATASTORE = "androidx.datastore:datastore-preferences:${Versions.dataStore}"
    const val COIL = "io.coil-kt:coil:${Versions.coil}"
    const val JAVAX_INJECT = "javax.inject:javax.inject:1"
}

fun DependencyHandler.retrofit() {
    implementation(Dependencies.RETROFIT)
    implementation(Dependencies.RETROFIT_GSON)
}

fun DependencyHandler.navigation() {
    implementation(Dependencies.NAVIGATION_FRAGMENT)
    implementation(Dependencies.NAVIGATION_UI)
}

fun DependencyHandler.coroutines() {
    implementation(Dependencies.COROUTINES_CORE)
    implementation(Dependencies.COROUTINES_ANDROID)
}

fun DependencyHandler.lifecycle() {
    implementation(Dependencies.LIFECYCLE_VIEWMODEL)
    implementation(Dependencies.LIFECYCLE_LIVEDATA)
    implementation(Dependencies.LIFECYCLE_SAVEDSTATE)
}

fun DependencyHandler.room() {
    implementation(Dependencies.ROOM_RUNTIME)
    implementation(Dependencies.ROOM_KTX)
    ksp(Dependencies.ROOM_COMPILER)
}

fun DependencyHandler.hilt() {
    implementation(Dependencies.HILT_ANDROID)
    kapt(Dependencies.HILT_COMPILER)
}

fun DependencyHandler.appCompat() {
    implementation(Dependencies.ANDROID_APP_COMPAT)
    implementation(Dependencies.ANDROID_CORE_KTX)
}
