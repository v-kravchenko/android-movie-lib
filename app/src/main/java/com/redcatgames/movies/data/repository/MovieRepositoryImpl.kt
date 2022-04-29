package com.redcatgames.movies.data.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.redcatgames.movies.data.source.local.dao.MovieDao
import com.redcatgames.movies.data.source.local.mapper.mapFrom
import com.redcatgames.movies.data.source.remote.NetworkService
import com.redcatgames.movies.data.source.remote.adapter.NetworkResponse
import com.redcatgames.movies.domain.model.Movie
import com.redcatgames.movies.domain.repository.MovieRepository
import timber.log.Timber

class MovieRepositoryImpl(
    private val movieDao: MovieDao,
    private val networkService: NetworkService
) : MovieRepository {

    override suspend fun loadPopularMovieList(): List<Movie> {

        val response = networkService.getPopularMovies()

        when (response) {
            is NetworkResponse.Success -> {
                val body = response.body
                Timber.d("Success: $body")
            }
            is NetworkResponse.ApiError -> {
                val message = response.body.statusMessage
                Timber.d("ApiError: $message")
            }
            is NetworkResponse.NetworkError -> {
                val message = response.error.localizedMessage
                Timber.d("NetworkError: $message")
            }
            is NetworkResponse.UnknownError -> {
                val message = response.error?.localizedMessage
                Timber.d("UnknownError: $message")
            }
        }

        return listOf()
    }

    override fun getPopularMovieList(): LiveData<List<Movie>> {
        return Transformations.map(movieDao.loadAll()) {
            it.map { movieEntity -> movieEntity.mapFrom() }
        }
    }
}