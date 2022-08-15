package com.redcatgames.movies.data.remote.interceptor

import com.redcatgames.movies.data.preferences.ImageConfigPreferences
import javax.inject.Inject
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response
import timber.log.Timber

class ImageHostSelectionInterceptor
@Inject
constructor(private val imageConfigPreferences: ImageConfigPreferences) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        var request: Request = chain.request()

        val imageConfig = imageConfigPreferences.currentConfig

        try {
            request =
                request
                    .newBuilder()
                    .url(request.url.toString().replace("https://localhost", imageConfig.secureBaseUrl))
                    .build()
        } catch (ex: Exception) {
            Timber.e(ex, "Image config url builder error")
        }

        return chain.proceed(request)
    }
}
