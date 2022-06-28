package com.redcatgames.movies.data.source.remote.interceptor

import com.redcatgames.movies.data.preferences.image.UserConfigPreferences
import com.redcatgames.movies.data.source.local.dao.PrimaryTranslationDao
import javax.inject.Inject
import kotlinx.coroutines.runBlocking
import okhttp3.Interceptor
import okhttp3.Response

class LanguageInterceptor
@Inject
constructor(
    private val userConfigPreferences: UserConfigPreferences,
    private val primaryTranslationDao: PrimaryTranslationDao
) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {

        val language = runBlocking {
            val apiLanguage = userConfigPreferences.readConfig().apiLanguage
            primaryTranslationDao.findByLanguage(apiLanguage)?.name ?: apiLanguage
        }

        var request = chain.request()
        val url = request.url().newBuilder().addQueryParameter("language", language).build()
        request = request.newBuilder().url(url).build()
        return chain.proceed(request)
    }
}
