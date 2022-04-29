package com.redcatgames.movies.data.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.redcatgames.movies.data.source.local.dao.ArtistDao
import com.redcatgames.movies.data.source.local.dao.MovieDao
import com.redcatgames.movies.data.source.local.mapper.mapFrom
import com.redcatgames.movies.data.source.local.mapper.mapTo
import com.redcatgames.movies.data.source.remote.NetworkService
import com.redcatgames.movies.domain.model.Artist
import com.redcatgames.movies.domain.model.Movie
import com.redcatgames.movies.domain.repository.ArtistRepository
import com.redcatgames.movies.domain.repository.MovieRepository
import timber.log.Timber

class MovieRepositoryImpl(
    private val movieDao: MovieDao,
    private val networkService: NetworkService
) : MovieRepository {

    override fun getPopularMovieList(): LiveData<List<Movie>> {

        val test = networkService.getPopularMovies()
        Timber.d("Movie count: ${test.isSuccessful}")

        return Transformations.map(movieDao.loadAll()) {
            it.map { movieEntity -> movieEntity.mapFrom() }
        }
    }
}