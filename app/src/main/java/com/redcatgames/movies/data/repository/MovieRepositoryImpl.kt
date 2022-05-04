package com.redcatgames.movies.data.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.redcatgames.movies.data.preferences.image.ImageConfigPreferences
import com.redcatgames.movies.data.source.local.dao.*
import com.redcatgames.movies.data.source.local.entity.MovieGenreEntity
import com.redcatgames.movies.data.source.local.mapper.mapFrom
import com.redcatgames.movies.data.source.local.mapper.mapTo
import com.redcatgames.movies.data.source.remote.NetworkService
import com.redcatgames.movies.data.source.remote.adapter.NetworkResponse
import com.redcatgames.movies.data.source.remote.mapper.mapFrom
import com.redcatgames.movies.domain.model.*
import com.redcatgames.movies.domain.repository.MovieRepository
import com.redcatgames.movies.domain.util.UseCaseResult
import com.redcatgames.movies.util.now
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.coroutineScope

class MovieRepositoryImpl(
    private val movieDao: MovieDao,
    private val movieGenreDao: MovieGenreDao,
    private val networkService: NetworkService
) : MovieRepository {

    override suspend fun deleteAllMovies(): UseCaseResult<Int, Unit> {
        val rowCount = movieDao.getCount()
        movieDao.deleteAll()
        return UseCaseResult.Success(rowCount)
    }

    override suspend fun putMovies(movies: List<Movie>) {
        movieDao.insertAll(movies.map { it.mapTo() })
        putMoviesGenres(movies)
    }

    private suspend fun putMovieGenres(movie: Movie) {
        movieGenreDao.deleteByMovie(movie.id)
        movieGenreDao.insertAll(movie.genreIds.map { MovieGenreEntity(movie.id, it, now()) })
    }

    private suspend fun putMoviesGenres(movies: List<Movie>) {
        val movieGenres = mutableListOf<MovieGenreEntity>()
        movies.forEach { movie ->
            movieGenres.addAll(movie.genreIds.map { MovieGenreEntity(movie.id, it, now()) })
        }
        movieGenreDao.insertAll(movieGenres)
    }

    override suspend fun putMovie(movie: Movie) {
        movieDao.insert(movie.mapTo())
        putMovieGenres(movie)
    }

    override suspend fun loadMovie(movieId: Long): UseCaseResult<Unit, String?> {

        return when (val response = networkService.getMovie(movieId)) {
            is NetworkResponse.Success -> {
                putMovie(response.body.mapFrom())
                UseCaseResult.Success(Unit)
            }
            is NetworkResponse.ApiError ->
                UseCaseResult.Failure(response.body.statusMessage)
            is NetworkResponse.NetworkError ->
                UseCaseResult.Failure(response.error.localizedMessage)
            is NetworkResponse.UnknownError ->
                UseCaseResult.Failure(response.error?.localizedMessage)
        }
    }

    override suspend fun loadPopularMovies(page: Int): UseCaseResult<List<Movie>, String?> {

        return when (val response = networkService.getPopularMovies(page)) {
            is NetworkResponse.Success -> {
                val movieList = response.body.movies.map { it.mapFrom() }
                deleteAllMovies()
                putMovies(movieList)
                UseCaseResult.Success(movieList)
            }
            is NetworkResponse.ApiError ->
                UseCaseResult.Failure(response.body.statusMessage)
            is NetworkResponse.NetworkError ->
                UseCaseResult.Failure(response.error.localizedMessage)
            is NetworkResponse.UnknownError ->
                UseCaseResult.Failure(response.error?.localizedMessage)
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