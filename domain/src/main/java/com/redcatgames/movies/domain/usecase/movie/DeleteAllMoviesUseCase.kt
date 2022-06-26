package com.redcatgames.movies.domain.usecase.movie

import com.redcatgames.movies.domain.repository.MovieRepository
import com.redcatgames.movies.domain.usecase.base.BaseUseCase
import kotlinx.coroutines.withContext
import javax.inject.Inject

class DeleteAllMoviesUseCase @Inject constructor(
    private val movieRepository: MovieRepository
) : BaseUseCase() {
    suspend operator fun invoke() = withContext(io) {
        movieRepository.deleteAllMovies()
    }
}