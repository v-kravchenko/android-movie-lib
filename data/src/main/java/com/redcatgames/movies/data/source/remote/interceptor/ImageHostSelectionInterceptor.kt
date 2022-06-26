package com.redcatgames.movies.data.source.remote.interceptor

import com.redcatgames.movies.data.preferences.image.ImageConfigPreferences
import kotlinx.coroutines.runBlocking
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response
import javax.inject.Inject

class ImageHostSelectionInterceptor @Inject constructor(
    private val imageConfigPreferences: ImageConfigPreferences
) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        var request: Request = chain.request()

        val host = runBlocking { imageConfigPreferences.readConfig() }

        request = request.newBuilder()
            .url(
                request.url().toString().replace(
                    "https://localhost", host.secureBaseUrl
                )
            )
            .build()

        return chain.proceed(request)
    }
}