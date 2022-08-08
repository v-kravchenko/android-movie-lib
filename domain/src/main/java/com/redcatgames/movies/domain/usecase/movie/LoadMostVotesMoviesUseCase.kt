package com.redcatgames.movies.domain.usecase.movie

import com.redcatgames.movies.domain.model.Movie
import com.redcatgames.movies.domain.repository.MovieRepository
import javax.inject.Inject

class LoadMostVotesMoviesUseCase @Inject constructor(private val movieRepository: MovieRepository) {
    suspend operator fun invoke(): Result<List<Movie>> = movieRepository.loadMostVotesMovies()
}
