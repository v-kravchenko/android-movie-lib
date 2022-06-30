package com.redcatgames.movies.data.preferences

import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.lifecycle.LiveData
import androidx.lifecycle.map
import com.redcatgames.movies.domain.model.ImageConfig
import com.redcatgames.movies.util.EMPTY_STRING
import com.redcatgames.movies.util.fromList
import com.redcatgames.movies.util.toList

class ImageConfigPreferences(private val preferences: Preferences) {

    companion object {
        private const val SEPARATOR = ","

        private const val BASE_URL_KEY = "image_config_base_url"
        private const val SECURE_BASE_URL_KEY = "image_config_secure_base_url"

        private const val BACKDROP_SIZES_KEY = "image_config_backdrop_sizes"
        private const val LOGO_SIZES_KEY = "image_config_logo_sizes"
        private const val POSTER_SIZES_KEY = "image_config_poster_sizes"
        private const val PROFILE_SIZES_KEY = "image_config_profile_sizes"
        private const val STILL_SIZES_KEY = "image_config_still_sizes"

        private const val DEFAULT_BASE_URL_VALUE = EMPTY_STRING
        private const val DEFAULT_SECURE_BASE_URL_VALUE = EMPTY_STRING

        private const val DEFAULT_BACKDROP_SIZES_VALUE = EMPTY_STRING
        private const val DEFAULT_LOGO_SIZES_VALUE = EMPTY_STRING
        private const val DEFAULT_POSTER_SIZES_VALUE = EMPTY_STRING
        private const val DEFAULT_PROFILE_SIZES_VALUE = EMPTY_STRING
        private const val DEFAULT_STILL_SIZES_VALUE = EMPTY_STRING
    }

    val imageConfig: LiveData<ImageConfig> =
        preferences.data.map {
            val keyBaseUrl = stringPreferencesKey(BASE_URL_KEY)
            val keySecureBaseUrl = stringPreferencesKey(SECURE_BASE_URL_KEY)

            val keyBackdropSizes = stringPreferencesKey(BACKDROP_SIZES_KEY)
            val keyLogoSizes = stringPreferencesKey(LOGO_SIZES_KEY)
            val keyPosterSizes = stringPreferencesKey(POSTER_SIZES_KEY)
            val keyProfileSizes = stringPreferencesKey(PROFILE_SIZES_KEY)
            val keyStillSizes = stringPreferencesKey(STILL_SIZES_KEY)

            ImageConfig(
                it[keyBaseUrl] ?: DEFAULT_BASE_URL_VALUE,
                it[keySecureBaseUrl] ?: DEFAULT_SECURE_BASE_URL_VALUE,
                (it[keyBackdropSizes] ?: DEFAULT_BACKDROP_SIZES_VALUE).toList(SEPARATOR),
                (it[keyLogoSizes] ?: DEFAULT_LOGO_SIZES_VALUE).toList(SEPARATOR),
                (it[keyPosterSizes] ?: DEFAULT_POSTER_SIZES_VALUE).toList(SEPARATOR),
                (it[keyProfileSizes] ?: DEFAULT_PROFILE_SIZES_VALUE).toList(SEPARATOR),
                (it[keyStillSizes] ?: DEFAULT_STILL_SIZES_VALUE).toList(SEPARATOR)
            )
        }

    suspend fun readConfig(): ImageConfig {
        return ImageConfig(
            preferences.getString(BASE_URL_KEY) ?: DEFAULT_BASE_URL_VALUE,
            preferences.getString(SECURE_BASE_URL_KEY) ?: DEFAULT_SECURE_BASE_URL_VALUE,
            (preferences.getString(BACKDROP_SIZES_KEY) ?: DEFAULT_BACKDROP_SIZES_VALUE).toList(
                SEPARATOR
            ),
            (preferences.getString(LOGO_SIZES_KEY) ?: DEFAULT_LOGO_SIZES_VALUE).toList(SEPARATOR),
            (preferences.getString(POSTER_SIZES_KEY) ?: DEFAULT_POSTER_SIZES_VALUE).toList(
                SEPARATOR
            ),
            (preferences.getString(PROFILE_SIZES_KEY) ?: DEFAULT_PROFILE_SIZES_VALUE).toList(
                SEPARATOR
            ),
            (preferences.getString(STILL_SIZES_KEY) ?: DEFAULT_STILL_SIZES_VALUE).toList(SEPARATOR)
        )
    }

    suspend fun putConfig(imageConfig: ImageConfig) {
        preferences.putString(BASE_URL_KEY, imageConfig.baseUrl)
        preferences.putString(SECURE_BASE_URL_KEY, imageConfig.secureBaseUrl)
        preferences.putString(BACKDROP_SIZES_KEY, imageConfig.backdropSizes.fromList(SEPARATOR))
        preferences.putString(LOGO_SIZES_KEY, imageConfig.logoSizes.fromList(SEPARATOR))
        preferences.putString(POSTER_SIZES_KEY, imageConfig.posterSizes.fromList(SEPARATOR))
        preferences.putString(PROFILE_SIZES_KEY, imageConfig.profileSizes.fromList(SEPARATOR))
        preferences.putString(STILL_SIZES_KEY, imageConfig.stillSizes.fromList(SEPARATOR))
    }
}
