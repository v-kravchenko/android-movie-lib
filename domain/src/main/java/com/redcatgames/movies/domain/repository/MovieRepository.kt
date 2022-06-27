package com.redcatgames.movies.domain.repository

import androidx.lifecycle.LiveData
import com.redcatgames.movies.domain.model.*

interface MovieRepository {
    suspend fun deleteAllMovies(): Result<Int>
    suspend fun putMovie(movie: Movie)
    suspend fun putMovies(movies: List<Movie>)

    suspend fun loadPopularMovies(page: Int): Result<List<Movie>>
    suspend fun loadMovie(movieId: Long): Result<Unit>
    suspend fun loadMovieCredits(movieId: Long): Result<Unit>
    suspend fun loadMovieInfo(movieId: Long): Result<Unit>

    fun popularMovies(): LiveData<List<Movie>>
    fun movie(movieId: Long): LiveData<Movie?>
    fun movieInfo(movieId: Long): LiveData<MovieInfo?>
    fun movieGenres(movieId: Long): LiveData<List<MovieGenre>>
    fun movieCasts(movieId: Long): LiveData<List<MovieCast>>
    fun movieCrews(movieId: Long): LiveData<List<MovieCrew>>
}
