package com.redcatgames.movies.data.remote.source.interceptor

import com.redcatgames.movies.data.preferences.source.image.UserConfigPreferences
import javax.inject.Inject
import kotlinx.coroutines.runBlocking
import okhttp3.Interceptor
import okhttp3.Response

class LanguageInterceptor
@Inject
constructor(
    private val userConfigPreferences: UserConfigPreferences,
) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {

        val language = runBlocking {
            val userConfig = userConfigPreferences.readConfig()
            userConfig.apiLanguage
        }

        var request = chain.request()
        val url = request.url().newBuilder().addQueryParameter("language", language).build()
        request = request.newBuilder().url(url).build()
        return chain.proceed(request)
    }
}
