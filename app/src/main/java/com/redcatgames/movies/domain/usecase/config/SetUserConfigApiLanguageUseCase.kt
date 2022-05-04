package com.redcatgames.movies.domain.usecase.config

import com.redcatgames.movies.data.preferences.image.UserConfigPreferences
import com.redcatgames.movies.domain.model.Language
import com.redcatgames.movies.domain.usecase.base.BaseUseCase
import javax.inject.Inject

class SetUserConfigApiLanguageUseCase @Inject constructor(
    private val userConfigPreferences: UserConfigPreferences
) : BaseUseCase() {
    suspend operator fun invoke(language: Language) {
        val userConfig = userConfigPreferences.readConfig()
            .copy(apiLanguage = language.iso)
        userConfigPreferences.putConfig(userConfig)
    }
}