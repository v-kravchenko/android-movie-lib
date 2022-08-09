package com.redcatgames.movies.domain.usecase.dictionary

import com.redcatgames.movies.domain.model.DictionaryInfo
import com.redcatgames.movies.domain.repository.DictionaryRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class DictionaryInfoUseCase
@Inject
constructor(private val dictionaryRepository: DictionaryRepository) {
    operator fun invoke(): Flow<DictionaryInfo?> = dictionaryRepository.dictionaryInfo()
}
