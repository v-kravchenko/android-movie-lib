package com.redcatgames.movies.domain.usecase.config

import com.redcatgames.movies.domain.repository.DictionaryRepository
import javax.inject.Inject

class GetUserConfigUseCase
@Inject
constructor(private val dictionaryRepository: DictionaryRepository) {
    suspend operator fun invoke() = dictionaryRepository.getUserConfig()
}
