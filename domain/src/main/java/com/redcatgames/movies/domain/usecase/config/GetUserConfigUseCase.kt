package com.redcatgames.movies.domain.usecase.config

import com.redcatgames.movies.domain.repository.DictionaryRepository
import com.redcatgames.movies.domain.usecase.base.BaseUseCase
import javax.inject.Inject

class GetUserConfigUseCase
@Inject
constructor(private val dictionaryRepository: DictionaryRepository) : BaseUseCase() {
    operator fun invoke() = dictionaryRepository.userConfig()
}
