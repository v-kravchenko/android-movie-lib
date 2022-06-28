package com.redcatgames.movies.data.source.remote.interceptor

import androidx.lifecycle.asFlow
import com.redcatgames.movies.data.preferences.image.UserConfigPreferences
import com.redcatgames.movies.data.source.local.dao.PrimaryTranslationDao
import javax.inject.Inject
import kotlinx.coroutines.flow.firstOrNull
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

        val userApiLanguage = runBlocking { userConfigPreferences.readConfig().apiLanguage }

        val primaryTranslation = runBlocking {
            primaryTranslationDao.findByLanguage(userApiLanguage).asFlow().firstOrNull()
        }

        var request = chain.request()
        val url =
            request
                .url()
                .newBuilder()
                .addQueryParameter("language", primaryTranslation?.name ?: userApiLanguage)
                .build()
        request = request.newBuilder().url(url).build()
        return chain.proceed(request)
    }
}
