package com.redcatgames.movies.domain.repository

import androidx.lifecycle.LiveData
import com.redcatgames.movies.domain.model.*

interface MovieRepository {
    suspend fun deleteAllMovies(): Result<Int>
    suspend fun putMovie(movie: com.redcatgames.movies.domain.model.Movie)
    suspend fun putMovies(movies: List<com.redcatgames.movies.domain.model.Movie>)

    suspend fun loadPopularMovies(page: Int): Result<List<com.redcatgames.movies.domain.model.Movie>>
    suspend fun loadMovie(movieId: Long): Result<Unit>
    suspend fun loadMovieCredits(movieId: Long): Result<Unit>
    suspend fun loadMovieInfo(movieId: Long): Result<Unit>

    fun popularMovies(): LiveData<List<com.redcatgames.movies.domain.model.Movie>>
    fun movie(movieId: Long): LiveData<com.redcatgames.movies.domain.model.Movie?>
    fun movieInfo(movieId: Long): LiveData<com.redcatgames.movies.domain.model.MovieInfo?>
    fun movieGenres(movieId: Long): LiveData<List<com.redcatgames.movies.domain.model.MovieGenre>>
    fun movieCasts(movieId: Long): LiveData<List<com.redcatgames.movies.domain.model.MovieCast>>
    fun movieCrews(movieId: Long): LiveData<List<com.redcatgames.movies.domain.model.MovieCrew>>
}