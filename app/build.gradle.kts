import java.io.FileInputStream
import java.util.*

plugins {
    id(BuildPlugins.ANDROID_APP)
    kotlin("android")
    kotlin("kapt")
    id(BuildPlugins.HILT)
    id(BuildPlugins.GRADLE_VERSIONS)
    id(BuildPlugins.KTFMT)
}

android {
    compileSdk = AppConfig.compileSdkVersion
    buildToolsVersion = AppConfig.buildToolsVersion

    defaultConfig {
        applicationId = AppConfig.id
        minSdk = AppConfig.minSdkVersion
        targetSdk = AppConfig.targetSdkVersion
        versionCode = AppConfig.versionCode
        versionName = AppConfig.versionName
    }

    signingConfigs {
        register("release") {
            val keystorePropertiesFile = file("../keystore.properties")

            if (!keystorePropertiesFile.exists()) {
                logger.warn("Release builds may not work: signing config not found.")
                return@register
            }

            val keystoreProperties = Properties()
            keystoreProperties.load(FileInputStream(keystorePropertiesFile))

            keyAlias = keystoreProperties["keyAlias"] as String
            keyPassword = keystoreProperties["keyPassword"] as String
            storeFile = file(keystoreProperties["storeFile"] as String)
            storePassword = keystoreProperties["storePassword"] as String
        }
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
            signingConfig = signingConfigs.getByName("release")
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_1_8.toString()
    }

    buildFeatures {
        viewBinding = true
    }

    kapt.correctErrorTypes = true
}

dependencies {

    implementation(project(":util"))
    implementation(project(":baseui"))
    implementation(project(":data"))
    implementation(project(":domain"))
    implementation(project(":presentation"))

    hilt()

    debugImplementation(Dependencies.LEAKCANARY)
    implementation(Dependencies.COIL)
    implementation(Dependencies.TIMBER)
}

fun isNonStable(version: String): Boolean {
    val stableKeyword = listOf("RELEASE", "FINAL", "GA").any { version.toUpperCase().contains(it) }
    val regex = "^[0-9,.v-]+(-r)?$".toRegex()
    val isStable = stableKeyword || regex.matches(version)
    return isStable.not()
}

tasks
    .named<com.github.benmanes.gradle.versions.updates.DependencyUpdatesTask>("dependencyUpdates")
    .configure {
        checkForGradleUpdate = true
        resolutionStrategy {
            componentSelection {
                all {
                    if (isNonStable(candidate.version) && !isNonStable(currentVersion)) {
                        reject("Release candidate")
                    }
                }
            }
        }
    }

hilt {
    enableAggregatingTask = true
}

ktfmt {
    kotlinLangStyle()
}