package com.redcatgames.movies.data.preferences

import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import com.redcatgames.movies.domain.model.UserConfig
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class UserConfigPreferences(private val preferences: Preferences) {

    companion object {
        private const val API_LANGUAGE = "user_api_language"
        private const val UI_DARK_MODE = "ui_dark_mode"

        private const val DEFAULT_API_LANGUAGE = "en"
        private const val DEFAULT_UI_DARK_MODE = -1
    }

    val userConfig: Flow<UserConfig> =
        preferences.data.map {
            val keyApiLanguage = stringPreferencesKey(API_LANGUAGE)
            val keyUiDarkMode = intPreferencesKey(UI_DARK_MODE)
            UserConfig(
                it[keyApiLanguage] ?: DEFAULT_API_LANGUAGE,
                it[keyUiDarkMode] ?: DEFAULT_UI_DARK_MODE)
        }

    suspend fun readConfig(): UserConfig {
        return UserConfig(
            preferences.getString(API_LANGUAGE) ?: DEFAULT_API_LANGUAGE,
            preferences.getInt(UI_DARK_MODE) ?: DEFAULT_UI_DARK_MODE)
    }

    suspend fun putConfig(userConfig: UserConfig) {
        preferences.putString(API_LANGUAGE, userConfig.apiLanguage)
        preferences.putInt(UI_DARK_MODE, userConfig.uiDarkMode)
    }
}
