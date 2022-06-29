package com.redcatgames.movies.domain.usecase.dictionary

import com.redcatgames.movies.domain.repository.DictionaryRepository
import com.redcatgames.movies.domain.usecase.base.BaseUseCase
import javax.inject.Inject

class LanguagesUseCase @Inject constructor(private val dictionaryRepository: DictionaryRepository) :
    BaseUseCase() {
    operator fun invoke() = dictionaryRepository.languages()
}
