package com.redcatgames.movies.data.preferences.image

import com.redcatgames.movies.data.preferences.Preferences
import com.redcatgames.movies.domain.model.ImageConfig
import com.redcatgames.movies.util.now

class ImageConfigPreferences(private val preferences: Preferences) {

    companion object {
        private const val BASE_URL_KEY = "image_config_base_url"
        private const val SECURE_BASE_URL_KEY = "image_config_secure_base_url"

        private const val DEFAULT_BASE_URL_VALUE = ""
        private const val DEFAULT_SECURE_BASE_URL_VALUE = ""
    }

    suspend fun putConfig(imageConfig: ImageConfig) {
        preferences.putString(BASE_URL_KEY, imageConfig.baseUrl)
        preferences.putString(SECURE_BASE_URL_KEY, imageConfig.secureBaseUrl)
    }

    suspend fun getConfig() : ImageConfig {
        return ImageConfig(
            preferences.getString(BASE_URL_KEY) ?: DEFAULT_BASE_URL_VALUE,
            preferences.getString(SECURE_BASE_URL_KEY) ?: DEFAULT_SECURE_BASE_URL_VALUE,
            listOf(),
            listOf(),
            listOf(),
            listOf(),
            listOf(),
            now()
        )
    }

}