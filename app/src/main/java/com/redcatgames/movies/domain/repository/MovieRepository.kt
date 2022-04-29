package com.redcatgames.movies.domain.repository

import androidx.lifecycle.LiveData
import com.redcatgames.movies.domain.model.Movie

interface MovieRepository {
    suspend fun loadPopularMovieList(): List<Movie>
    fun popularMovieList(): LiveData<List<Movie>>
}