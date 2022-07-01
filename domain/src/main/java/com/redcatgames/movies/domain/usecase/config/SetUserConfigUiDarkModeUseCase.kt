package com.redcatgames.movies.domain.usecase.config

import com.redcatgames.movies.domain.repository.DictionaryRepository
import javax.inject.Inject

class SetUserConfigUiDarkModeUseCase
@Inject
constructor(private val dictionaryRepository: DictionaryRepository) {
    suspend operator fun invoke(darkMode: Int) = dictionaryRepository.putUserUiDarkMode(darkMode)
}
