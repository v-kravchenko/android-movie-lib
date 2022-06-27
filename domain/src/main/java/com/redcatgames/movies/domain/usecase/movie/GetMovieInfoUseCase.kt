package com.redcatgames.movies.domain.usecase.movie

import com.redcatgames.movies.domain.repository.MovieRepository
import com.redcatgames.movies.domain.usecase.base.BaseUseCase
import javax.inject.Inject

class GetMovieInfoUseCase @Inject constructor(private val movieRepository: MovieRepository) :
    BaseUseCase() {
    operator fun invoke(movieId: Long) = movieRepository.movieInfo(movieId)
}