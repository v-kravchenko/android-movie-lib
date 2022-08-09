package com.redcatgames.movies.domain.usecase.movie

import com.redcatgames.movies.domain.model.Movie
import com.redcatgames.movies.domain.repository.MovieRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetPopularMoviesUseCase @Inject constructor(private val movieRepository: MovieRepository) {
    operator fun invoke(): Flow<List<Movie>> = movieRepository.popularMovies()
}
