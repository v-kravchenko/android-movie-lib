package com.redcatgames.movies.domain.repository

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
    suspend fun loadPersonCredits(personId: Long): Result<Unit>
    suspend fun loadPersonInfo(personId: Long): Result<Unit>

    fun popularMovies(): Flow<List<Movie>>
    fun mostVotesMovies(): Flow<List<Movie>>
    fun movie(movieId: Long): Flow<Movie?>
    fun movieInfo(movieId: Long): Flow<MovieInfo?>
    fun movieGenres(movieId: Long): Flow<List<MovieGenre>>
    fun movieCasts(movieId: Long): Flow<List<MovieCast>>
    fun movieCrews(movieId: Long): Flow<List<MovieCrew>>
    fun person(personId: Long): Flow<Person?>
    fun personCasts(personId: Long): Flow<List<PersonCast>>
    fun personCrews(personId: Long): Flow<List<PersonCrew>>
    fun personInfo(personId: Long): Flow<PersonInfo?>
}
