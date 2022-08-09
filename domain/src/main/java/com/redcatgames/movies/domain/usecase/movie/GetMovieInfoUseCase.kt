package com.redcatgames.movies.domain.usecase.movie

import com.redcatgames.movies.domain.model.MovieInfo
import com.redcatgames.movies.domain.repository.MovieRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetMovieInfoUseCase @Inject constructor(private val movieRepository: MovieRepository) {
    operator fun invoke(movieId: Long): Flow<MovieInfo?> = movieRepository.movieInfo(movieId)
}
