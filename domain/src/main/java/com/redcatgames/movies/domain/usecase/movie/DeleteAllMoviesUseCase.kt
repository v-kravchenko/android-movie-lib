package com.redcatgames.movies.domain.usecase.movie

import com.redcatgames.movies.domain.repository.MovieRepository
import com.redcatgames.movies.domain.usecase.base.BaseUseCase
import javax.inject.Inject
import kotlinx.coroutines.withContext

class DeleteAllMoviesUseCase @Inject constructor(private val movieRepository: MovieRepository) :
    BaseUseCase() {
  suspend operator fun invoke() = withContext(io) { movieRepository.deleteAllMovies() }
}
