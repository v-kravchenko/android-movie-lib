package com.redcatgames.movies.domain.usecase.movie

import com.redcatgames.movies.domain.repository.MovieRepository
import javax.inject.Inject

class GetMovieInfoUseCase @Inject constructor(private val movieRepository: MovieRepository) {
    operator fun invoke(movieId: Long) = movieRepository.movieInfo(movieId)
}
