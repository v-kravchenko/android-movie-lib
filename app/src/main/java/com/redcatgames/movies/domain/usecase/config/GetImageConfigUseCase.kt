package com.redcatgames.movies.domain.usecase.config

import com.redcatgames.movies.domain.repository.MovieRepository
import com.redcatgames.movies.domain.usecase.base.BaseUseCase
import javax.inject.Inject

class GetImageConfigUseCase @Inject constructor(
    private val movieRepository: MovieRepository
) : BaseUseCase() {
    operator fun invoke() = movieRepository.imageConfig()
}