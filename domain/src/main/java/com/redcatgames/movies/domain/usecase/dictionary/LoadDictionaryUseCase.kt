package com.redcatgames.movies.domain.usecase.dictionary

import com.redcatgames.movies.domain.repository.DictionaryRepository
import javax.inject.Inject

class LoadDictionaryUseCase
@Inject
constructor(private val dictionaryRepository: DictionaryRepository) {
    suspend operator fun invoke() = dictionaryRepository.loadDictionary()
}
