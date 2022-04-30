package com.redcatgames.movies.domain.repository

import androidx.lifecycle.LiveData
import com.redcatgames.movies.domain.model.Movie
import com.redcatgames.movies.domain.util.UseCaseResult

interface MovieRepository {
    suspend fun deleteAllMovies()
    suspend fun putMovie(movie: Movie)
    suspend fun putMovieList(movies: List<Movie>)
    suspend fun loadPopularMovieList(): UseCaseResult<Int>
    fun popularMovieList(): LiveData<List<Movie>>
}