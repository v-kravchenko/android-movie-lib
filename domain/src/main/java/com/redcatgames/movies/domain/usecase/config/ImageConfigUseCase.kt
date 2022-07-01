package com.redcatgames.movies.domain.usecase.config

import com.redcatgames.movies.domain.repository.DictionaryRepository
import javax.inject.Inject

class ImageConfigUseCase
@Inject
constructor(private val dictionaryRepository: DictionaryRepository) {
    operator fun invoke() = dictionaryRepository.imageConfig()
}
