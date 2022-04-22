package com.redcatgames.musiclib;

import android.app.Application;
import android.os.Build
import dagger.hilt.android.HiltAndroidApp
import timber.log.Timber

@HiltAndroidApp
class MusicLibApp: Application() {
    override fun onCreate() {
        super.onCreate()
        loadTimber()
    }

    private fun loadTimber() {
        if (BuildConfig.DEBUG) {
            Timber.plant(object : Timber.DebugTree() {
                override fun createStackElementTag(element: StackTraceElement): String {
                    return "${element.fileName}[L:${element.lineNumber}] ${
                        super.createStackElementTag(
                            element
                        )
                    }"
                }

                override fun log(
                    priority: Int, tag: String?, message: String, t: Throwable?
                ) {
                    super.log(priority, BuildConfig.APPLICATION_ID, "$tag $message", t)
                }
            })
        }

        Timber.d("Start application version: ${BuildConfig.VERSION_NAME} (code: ${BuildConfig.VERSION_CODE}) ${if (BuildConfig.DEBUG) "DEBUG" else "RELEASE"}\"")
        Timber.d("Device: ${Build.MANUFACTURER} ${Build.MODEL} - Android ${Build.VERSION.RELEASE} (API ${Build.VERSION.SDK_INT})")
    }
}
