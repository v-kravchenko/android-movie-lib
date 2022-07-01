package com.redcatgames.movies.domain.usecase.dictionary

import com.redcatgames.movies.domain.repository.DictionaryRepository
import javax.inject.Inject

class GetLanguageUseCase
@Inject
constructor(private val dictionaryRepository: DictionaryRepository) {
    suspend operator fun invoke(iso: String) = dictionaryRepository.getLanguage(iso)
}
