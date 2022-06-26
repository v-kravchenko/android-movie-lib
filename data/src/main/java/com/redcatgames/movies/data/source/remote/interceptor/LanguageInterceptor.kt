package com.redcatgames.movies.data.source.remote.interceptor

import com.redcatgames.movies.data.preferences.image.UserConfigPreferences
import kotlinx.coroutines.runBlocking
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

class LanguageInterceptor @Inject constructor(
    private val userConfigPreferences: UserConfigPreferences) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {

        val userApiLanguage = runBlocking { userConfigPreferences.readConfig().apiLanguage }

        var request = chain.request()
        val url = request.url().newBuilder().addQueryParameter("language", userApiLanguage).build()
        request = request.newBuilder().url(url).build()
        return chain.proceed(request)
    }

}