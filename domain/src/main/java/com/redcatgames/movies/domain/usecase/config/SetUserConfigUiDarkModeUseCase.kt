package com.redcatgames.movies.domain.usecase.config

import com.redcatgames.movies.domain.repository.DictionaryRepository
import com.redcatgames.movies.domain.usecase.base.BaseUseCase
import javax.inject.Inject
import kotlinx.coroutines.withContext

class SetUserConfigUiDarkModeUseCase
@Inject
constructor(private val dictionaryRepository: DictionaryRepository) : BaseUseCase() {
    suspend operator fun invoke(darkMode: Int) =
        withContext(io) { dictionaryRepository.putUserUiDarkMode(darkMode) }
}
