package com.redcatgames.movies.data.preferences.image

import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.lifecycle.LiveData
import androidx.lifecycle.map
import com.redcatgames.movies.data.preferences.Preferences
import com.redcatgames.movies.domain.model.UserConfig

class UserConfigPreferences(private val preferences: Preferences) {

    companion object {
        private const val API_LANGUAGE = "user_api_language"

        private const val DEFAULT_API_LANGUAGE = "ru"
    }

    val userConfig: LiveData<UserConfig> =
        preferences.data.map {
            val keyApiLanguage = stringPreferencesKey(API_LANGUAGE)
            UserConfig(it[keyApiLanguage] ?: DEFAULT_API_LANGUAGE)
        }

    suspend fun readConfig(): UserConfig {
        return UserConfig(preferences.getString(API_LANGUAGE) ?: DEFAULT_API_LANGUAGE)
    }

    suspend fun putConfig(userConfig: UserConfig) {
        preferences.putString(API_LANGUAGE, userConfig.apiLanguage)
    }
}