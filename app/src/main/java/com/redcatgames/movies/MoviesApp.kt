package com.redcatgames.movies

import android.app.Application
import android.os.Build
import coil.ImageLoader
import coil.ImageLoaderFactory
import dagger.hilt.android.HiltAndroidApp
import javax.inject.Inject
import timber.log.Timber

@HiltAndroidApp
class MoviesApp : Application(), ImageLoaderFactory {

  @Inject lateinit var imageLoader: ImageLoader

  override fun onCreate() {
    super.onCreate()
    loadTimber()
  }

  override fun newImageLoader(): ImageLoader {
    return imageLoader
  }

  private fun loadTimber() {
    if (BuildConfig.DEBUG) {
      Timber.plant(
          object : Timber.DebugTree() {
            override fun createStackElementTag(element: StackTraceElement): String {
              return "${element.fileName}[L:${element.lineNumber}] ${
                        super.createStackElementTag(
                            element
                        )
                    }"
            }

            override fun log(priority: Int, tag: String?, message: String, t: Throwable?) {
              super.log(priority, BuildConfig.APPLICATION_ID, "$tag $message", t)
            }
          })
    }

    Timber.d(
        "Start application version: ${BuildConfig.VERSION_NAME} (code: ${BuildConfig.VERSION_CODE}) ${if (BuildConfig.DEBUG) "DEBUG" else "RELEASE"}\"")
    Timber.d(
        "Device: ${Build.MANUFACTURER} ${Build.MODEL} - Android ${Build.VERSION.RELEASE} (API ${Build.VERSION.SDK_INT})")
  }
}
