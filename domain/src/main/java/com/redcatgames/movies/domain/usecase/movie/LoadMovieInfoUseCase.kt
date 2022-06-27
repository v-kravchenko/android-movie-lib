package com.redcatgames.movies.domain.usecase.movie

import com.redcatgames.movies.domain.repository.MovieRepository
import com.redcatgames.movies.domain.usecase.base.BaseUseCase
import javax.inject.Inject
import kotlinx.coroutines.withContext

class LoadMovieInfoUseCase @Inject constructor(private val movieRepository: MovieRepository) :
    BaseUseCase() {
    suspend operator fun invoke(movieId: Long) =
        withContext(io) { movieRepository.loadMovieInfo(movieId) }
}
