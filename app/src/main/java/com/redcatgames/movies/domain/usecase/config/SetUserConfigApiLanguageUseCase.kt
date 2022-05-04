package com.redcatgames.movies.domain.usecase.config

import com.redcatgames.movies.data.preferences.image.UserConfigPreferences
import com.redcatgames.movies.domain.usecase.base.BaseUseCase
import javax.inject.Inject

class SetUserConfigApiLanguageUseCase @Inject constructor(
    private val userConfigPreferences: UserConfigPreferences
) : BaseUseCase() {
    suspend operator fun invoke(apiLanguage: String) {
        val userConfig = userConfigPreferences.readConfig()
            .copy(apiLanguage = apiLanguage)
        userConfigPreferences.putConfig(userConfig)
    }
}