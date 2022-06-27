package com.redcatgames.movies.domain.usecase.dictionary

import com.redcatgames.movies.domain.repository.DictionaryRepository
import com.redcatgames.movies.domain.usecase.base.BaseUseCase
import javax.inject.Inject
import kotlinx.coroutines.withContext

class DeleteDictionaryUseCase
@Inject
constructor(private val dictionaryRepository: DictionaryRepository) : BaseUseCase() {
  suspend operator fun invoke() = withContext(io) { dictionaryRepository.deleteAll() }
}
