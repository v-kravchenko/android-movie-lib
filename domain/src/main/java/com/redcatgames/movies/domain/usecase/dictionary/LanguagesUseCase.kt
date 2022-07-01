package com.redcatgames.movies.domain.usecase.dictionary

import com.redcatgames.movies.domain.repository.DictionaryRepository
import javax.inject.Inject

class LanguagesUseCase @Inject constructor(private val dictionaryRepository: DictionaryRepository) {
    operator fun invoke() = dictionaryRepository.languages()
}
