package com.redcatgames.movies.domain.usecase.config

import com.redcatgames.movies.domain.model.Language
import com.redcatgames.movies.domain.repository.DictionaryRepository
import com.redcatgames.movies.domain.usecase.base.BaseUseCase
import javax.inject.Inject

class SetUserConfigApiLanguageUseCase
@Inject
constructor(private val dictionaryRepository: DictionaryRepository) : BaseUseCase() {
    suspend operator fun invoke(language: Language) {
        dictionaryRepository.putUserApiLanguage(language)
    }
}
