package com.redcatgames.movies.domain.repository

import androidx.lifecycle.LiveData
import com.redcatgames.movies.domain.model.Movie

interface MovieRepository {
    fun getPopularMovieList(): LiveData<List<Movie>>
}