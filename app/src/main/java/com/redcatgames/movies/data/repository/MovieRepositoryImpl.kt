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

    override suspend fun deleteAllMovies(): UseCaseResult<Int> {
        val movieCount = movieDao.getCount()
        movieDao.deleteAll()
        return UseCaseResult.Success(movieCount)
    }

    override suspend fun putMovie(movie: Movie) {
        movieDao.insert(movie.mapTo())
    }

    override suspend fun putMovies(movies: List<Movie>) {
        movieDao.insertAll(movies.map { it.mapTo() })
    }

    override suspend fun loadMovie(movieId: Long): UseCaseResult<Unit> {

        return when (val response = networkService.getMovie(movieId)) {
            is NetworkResponse.Success -> {
                Timber.d("onSuccess: load movie #$movieId")
                val movie = response.body.mapFrom()
                putMovie(movie)
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
    }

    override suspend fun loadPopularMovies(page: Int): UseCaseResult<List<Movie>> {

        return when (val response = networkService.getPopularMovies(page)) {
            is NetworkResponse.Success -> {
                Timber.d("onSuccess: loaded movie count: ${response.body.movies.size}")
                val movieList = response.body.movies.map { it.mapFrom() }
                deleteAllMovies()
                putMovies(movieList)
                UseCaseResult.Success(movieList)
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
    }

    override fun popularMovies(): LiveData<List<Movie>> {
        return Transformations.map(movieDao.getPopular()) {
            it.map { movieEntity -> movieEntity.mapFrom() }
        }
    }

    override fun movie(movieId: Long): LiveData<Movie?> {
        return Transformations.map(movieDao.getById(movieId)) {
            it?.mapFrom()
        }
    }
}