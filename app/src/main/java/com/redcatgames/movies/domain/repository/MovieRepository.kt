package com.redcatgames.movies.domain.repository

import androidx.lifecycle.LiveData
import com.redcatgames.movies.domain.model.ImageConfig
import com.redcatgames.movies.domain.model.Movie
import com.redcatgames.movies.domain.util.UseCaseResult

interface MovieRepository {
    suspend fun deleteImageConfig()
    suspend fun putImageConfig(imageConfig: ImageConfig)
    fun imageConfig(): LiveData<ImageConfig?>
    suspend fun loadConfig(): UseCaseResult<Unit>

    suspend fun deleteAllMovies(): UseCaseResult<Int>
    suspend fun putMovie(movie: Movie)
    suspend fun putMovies(movies: List<Movie>)
    suspend fun loadPopularMovies(page: Int): UseCaseResult<List<Movie>>
    suspend fun loadMovie(movieId: Long): UseCaseResult<Unit>
    fun popularMovies(): LiveData<List<Movie>>
    fun movie(movieId: Long): LiveData<Movie?>
}