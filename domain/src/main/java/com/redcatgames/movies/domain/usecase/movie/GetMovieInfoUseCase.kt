package com.redcatgames.movies.domain.usecase.movie

import androidx.lifecycle.LiveData
import com.redcatgames.movies.domain.model.MovieInfo
import com.redcatgames.movies.domain.repository.MovieRepository
import javax.inject.Inject

class GetMovieInfoUseCase @Inject constructor(private val movieRepository: MovieRepository) {
    operator fun invoke(movieId: Long): LiveData<MovieInfo?> = movieRepository.movieInfo(movieId)
}
