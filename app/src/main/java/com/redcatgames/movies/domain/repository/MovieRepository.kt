package com.redcatgames.movies.domain.repository

import androidx.lifecycle.LiveData
import com.redcatgames.movies.domain.model.ImageConfig
import com.redcatgames.movies.domain.model.Movie
import com.redcatgames.movies.domain.util.UseCaseResult

interface MovieRepository {
    suspend fun loadDictionary(): UseCaseResult<Unit, String?>

    suspend fun putImageConfig(imageConfig: ImageConfig)
    fun imageConfig(): LiveData<ImageConfig>
    suspend fun loadConfig(): UseCaseResult<Unit, String?>

    suspend fun deleteAllMovies(): UseCaseResult<Int, Unit>
    suspend fun putMovie(movie: Movie)
    suspend fun putMovies(movies: List<Movie>)
    suspend fun loadPopularMovies(page: Int): UseCaseResult<List<Movie>, String?>
    suspend fun loadMovie(movieId: Long): UseCaseResult<Unit, String?>
    fun popularMovies(): LiveData<List<Movie>>
    fun movie(movieId: Long): LiveData<Movie?>
}