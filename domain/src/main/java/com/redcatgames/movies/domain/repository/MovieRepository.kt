package com.redcatgames.movies.domain.repository

import androidx.lifecycle.LiveData
import com.redcatgames.movies.domain.model.*
import kotlinx.coroutines.flow.Flow

interface MovieRepository {
    suspend fun deleteAllMovies(): Result<Int>
    suspend fun putMovie(movie: Movie)
    suspend fun putMovies(movies: List<Movie>)
    suspend fun putPerson(person: Person)

    suspend fun loadPopularMovies(): Result<List<Movie>>
    suspend fun loadMostVotesMovies(): Result<List<Movie>>
    suspend fun loadMovie(movieId: Long): Result<Unit>
    suspend fun loadMovieCredits(movieId: Long): Result<Unit>
    suspend fun loadMovieInfo(movieId: Long): Result<Unit>
    suspend fun loadPerson(personId: Long): Result<Unit>

    fun popularMovies(): LiveData<List<Movie>>
    fun popularMoviesFlow(): Flow<List<Movie>>
    fun mostVotesMovies(): LiveData<List<Movie>>
    fun movie(movieId: Long): LiveData<Movie?>
    fun movieInfo(movieId: Long): LiveData<MovieInfo?>
    fun movieGenres(movieId: Long): LiveData<List<MovieGenre>>
    fun movieCasts(movieId: Long): LiveData<List<MovieCast>>
    fun movieCrews(movieId: Long): LiveData<List<MovieCrew>>
    fun person(personId: Long): LiveData<Person?>
}
