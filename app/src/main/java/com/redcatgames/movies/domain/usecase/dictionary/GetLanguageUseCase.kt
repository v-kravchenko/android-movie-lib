package com.redcatgames.movies.domain.usecase.dictionary

import com.redcatgames.movies.domain.repository.DictionaryRepository
import com.redcatgames.movies.domain.repository.MovieRepository
import com.redcatgames.movies.domain.usecase.base.BaseUseCase
import kotlinx.coroutines.withContext
import javax.inject.Inject

class GetLanguageUseCase @Inject constructor(
    private val dictionaryRepository: DictionaryRepository
) : BaseUseCase() {
    operator fun invoke(iso: String) = dictionaryRepository.getLanguage(iso)
}