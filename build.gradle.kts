import com.ncorti.ktfmt.gradle.tasks.*

buildscript {
    repositories {
        gradlePluginPortal()
        google()
        mavenCentral()
    }
    dependencies {
        classpath(BuildClass.ANDROID_APP)
        classpath(BuildClass.KOTLIN)
        classpath(BuildClass.HILT)
        classpath(BuildClass.KSP)
        classpath(BuildClass.SAFEARGS)
        classpath(BuildClass.GRADLEVERSION)
        classpath(BuildClass.KTFMT)
    }
}

allprojects {
    repositories {
        google()
        mavenCentral()
    }
}

tasks.register("clean", Delete::class) { delete(rootProject.buildDir) }

tasks.register<KtfmtFormatTask>("ktfmtPrecommit") {
    source = project.fileTree(rootDir)
    include("**/*.kt")
}