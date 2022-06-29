package com.redcatgames.movies.data.preferences.source.image

import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.lifecycle.LiveData
import androidx.lifecycle.map
import com.redcatgames.movies.data.preferences.source.Preferences
import com.redcatgames.movies.domain.model.ImageConfig

class ImageConfigPreferences(private val preferences: Preferences) {

    companion object {
        private const val BASE_URL_KEY = "image_config_base_url"
        private const val SECURE_BASE_URL_KEY = "image_config_secure_base_url"

        private const val DEFAULT_BASE_URL_VALUE = ""
        private const val DEFAULT_SECURE_BASE_URL_VALUE = ""
    }

    val imageConfig: LiveData<ImageConfig> =
        preferences.data.map {
            val keyBaseUrl = stringPreferencesKey(BASE_URL_KEY)
            val keySecureBaseUrl = stringPreferencesKey(SECURE_BASE_URL_KEY)
            ImageConfig(
                it[keyBaseUrl] ?: DEFAULT_BASE_URL_VALUE,
                it[keySecureBaseUrl] ?: DEFAULT_SECURE_BASE_URL_VALUE,
                listOf(),
                listOf(),
                listOf(),
                listOf(),
                listOf()
            )
        }

    suspend fun readConfig(): ImageConfig {
        return ImageConfig(
            preferences.getString(BASE_URL_KEY) ?: DEFAULT_BASE_URL_VALUE,
            preferences.getString(SECURE_BASE_URL_KEY) ?: DEFAULT_SECURE_BASE_URL_VALUE,
            listOf(),
            listOf(),
            listOf(),
            listOf(),
            listOf()
        )
    }

    suspend fun putConfig(imageConfig: ImageConfig) {
        preferences.putString(BASE_URL_KEY, imageConfig.baseUrl)
        preferences.putString(SECURE_BASE_URL_KEY, imageConfig.secureBaseUrl)
    }
}
