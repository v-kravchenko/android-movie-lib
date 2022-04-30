package com.redcatgames.movies.data.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.redcatgames.movies.data.source.local.dao.MovieDao
import com.redcatgames.movies.data.source.local.mapper.mapFrom
import com.redcatgames.movies.data.source.local.mapper.mapTo
import com.redcatgames.movies.data.source.remote.NetworkService
import com.redcatgames.movies.data.source.remote.adapter.NetworkResponse
import com.redcatgames.movies.data.source.remote.mapper.mapFrom
import com.redcatgames.movies.domain.model.Movie
import com.redcatgames.movies.domain.repository.MovieRepository
import com.redcatgames.movies.domain.util.UseCaseResult
import timber.log.Timber

class MovieRepositoryImpl(
    private val movieDao: MovieDao,
    private val networkService: NetworkService
) : MovieRepository {

    override suspend fun deleteAllMovies() {
        movieDao.deleteAll()
    }

    override suspend fun putMovie(movie: Movie) {
        movieDao.insert(movie.mapTo())
    }

    override suspend fun putMovieList(movies: List<Movie>) {
        movieDao.insertAll(movies.map { it.mapTo() })
    }

    override suspend fun loadPopularMovieList(): UseCaseResult<Unit> {

        return when (val response = networkService.getPopularMovies()) {
            is NetworkResponse.Success -> {
                Timber.d("onSuccess: loaded movie count: ${response.body.movies.size}")
                deleteAllMovies()
                val movieList = response.body.movies.map { it.mapFrom() }
                putMovieList(movieList)
                UseCaseResult.Success(Unit)
            }
            is NetworkResponse.ApiError -> {
                val message = response.body.statusMessage
                Timber.e("ApiError: $message")
                UseCaseResult.Failure(response.body.statusMessage)
            }
            is NetworkResponse.NetworkError -> {
                val message = response.error.localizedMessage
                Timber.e("NetworkError: $message")
                UseCaseResult.Failure(response.error.localizedMessage)
            }
            is NetworkResponse.UnknownError -> {
                val message = response.error?.localizedMessage
                Timber.e("UnknownError: $message")
                UseCaseResult.Failure(response.error?.localizedMessage)
            }
        }

//        response.onSuccess { result ->
//            Timber.d("onSuccess: loaded movie count: ${result.movies.size}")
//            deleteAllMovies()
//            val movieList = result.movies.map { it.mapFrom() }
//            putMovieList(movieList)
//            return UseCaseResult.Success(Unit)
//        }
//
//        response.onApiError { error, _ ->
//            return UseCaseResult.Failure(error.statusMessage)
//        }
//
//        response.onNetworkError {
//            return UseCaseResult.Failure(it.localizedMessage ?: "Network error")
//        }
//
//        response.onUnknownError {
//            return UseCaseResult.Failure(it?.localizedMessage ?: "Unknown error")
//        }
    }

    override fun popularMovieList(): LiveData<List<Movie>> {
        return Transformations.map(movieDao.loadAll()) {
            it.map { movieEntity -> movieEntity.mapFrom() }
        }
    }
}