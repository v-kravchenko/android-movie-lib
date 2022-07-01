package com.redcatgames.movies.domain.usecase.config

import com.redcatgames.movies.domain.model.Language
import com.redcatgames.movies.domain.repository.DictionaryRepository
import javax.inject.Inject

class SetUserConfigApiLanguageUseCase
@Inject
constructor(private val dictionaryRepository: DictionaryRepository) {
    suspend operator fun invoke(language: Language) =
        dictionaryRepository.putUserApiLanguage(language)
}
