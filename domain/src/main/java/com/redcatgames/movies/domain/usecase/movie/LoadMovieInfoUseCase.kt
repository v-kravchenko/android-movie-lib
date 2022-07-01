package com.redcatgames.movies.domain.usecase.movie

import com.redcatgames.movies.domain.repository.MovieRepository
import javax.inject.Inject

class LoadMovieInfoUseCase @Inject constructor(private val movieRepository: MovieRepository) {
    suspend operator fun invoke(movieId: Long) = movieRepository.loadMovieInfo(movieId)
}
