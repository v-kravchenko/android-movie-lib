package com.redcatgames.movies.data.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.asFlow
import com.redcatgames.movies.data.source.local.dao.*
import com.redcatgames.movies.data.source.local.mapper.fromEntity
import com.redcatgames.movies.data.source.local.mapper.toEntity
import com.redcatgames.movies.data.source.remote.NetworkService
import com.redcatgames.movies.data.source.remote.adapter.NetworkResponse
import com.redcatgames.movies.data.source.remote.mapper.toMovie
import com.redcatgames.movies.data.source.remote.mapper.toMovieGenre
import com.redcatgames.movies.data.source.remote.mapper.toMovieCastList
import com.redcatgames.movies.data.source.remote.mapper.toMovieCrewList
import com.redcatgames.movies.domain.model.*
import com.redcatgames.movies.domain.repository.MovieRepository
import com.redcatgames.movies.domain.util.UseCaseResult
import com.redcatgames.movies.util.empty
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.firstOrNull

class MovieRepositoryImpl(
    private val movieDao: MovieDao,
    private val movieGenreDao: MovieGenreDao,
    private val movieCastDao: MovieCastDao,
    private val movieCrewDao: MovieCrewDao,
    private val genreDao: GenreDao,
    private val networkService: NetworkService
) : MovieRepository {

    override suspend fun deleteAllMovies(): UseCaseResult<Int, Unit> {
        val rowCount = movieDao.getCount()
        movieDao.deleteAll()
        return UseCaseResult.Success(rowCount)
    }

    override suspend fun putMovies(movies: List<Movie>) {
        movieDao.insertAll(movies.map { it.toEntity() })
    }

    private suspend fun putMovieGenres(movie: Movie, genres: List<MovieGenre>) {
        val localGenres = movieGenres(movie.id).asFlow().first()
        if (localGenres.sortedBy { it.genreId } != genres.sortedBy { it.genreId }) {
            movieGenreDao.deleteByMovie(movie.id)
            movieGenreDao.insertAll(genres.map { it.toEntity() })
        }
    }

    private suspend fun putMovieCasts(movieId: Long, casts: List<MovieCast>) {
        val localCasts = movieCasts(movieId).asFlow().first()
        if (localCasts.sortedBy { it.id } != casts.sortedBy { it.id }) {
            movieCastDao.deleteByMovie(movieId)
            movieCastDao.insertAll(casts.map { it.toEntity() })
        }
    }

    private suspend fun putMovieCrews(movieId: Long, crews: List<MovieCrew>) {
        val localCrews = movieCrews(movieId).asFlow().first()
        if (localCrews.sortedBy { it.id } != crews.sortedBy { it.id }) {
            movieCrewDao.deleteByMovie(movieId)
            movieCrewDao.insertAll(crews.map { it.toEntity() })
        }
    }

    private suspend fun putMoviesGenres(movies: List<Movie>, moviesGenres: MutableList<MovieGenre>) {
        movieGenreDao.deleteByMovieList(movies.map { it.id })
        movieGenreDao.insertAll(moviesGenres.map { it.toEntity() })
    }

    override suspend fun putMovie(movie: Movie) {
        if (movie != movie(movie.id).asFlow().firstOrNull()) {
            movieDao.insert(movie.toEntity())
        }
    }

    override suspend fun loadMovieInfo(movieId: Long): UseCaseResult<Unit, String?> {
        return coroutineScope {

            val jobList = listOf(
                async { loadMovie(movieId) },
                async { loadMovieCredits(movieId) }
            ).awaitAll()

            jobList.find { it.isFailure }?.let {
                if (it is UseCaseResult.Failure) {
                    return@coroutineScope UseCaseResult.Failure(it.error)
                }
            }

            UseCaseResult.Success(Unit)
        }
    }

    override suspend fun loadMovieCredits(movieId: Long): UseCaseResult<Unit, String?> {
        return when(val response = networkService.getMovieCredits(movieId)) {
            is NetworkResponse.Success -> {
                val castList = response.body.toMovieCastList()
                val crewList = response.body.toMovieCrewList()
                putMovieCasts(movieId, castList)
                putMovieCrews(movieId, crewList)
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

    override suspend fun loadMovie(movieId: Long): UseCaseResult<Unit, String?> {

        return when (val response = networkService.getMovie(movieId)) {
            is NetworkResponse.Success -> {
                val movie = response.body.toMovie()
                val genres = response.body.genres.map { it.toMovieGenre(movie) }
                putMovie(movie)
                putMovieGenres(movie, genres)
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

                val movies = response.body.movies.map { it.toMovie() }
                deleteAllMovies()
                putMovies(movies)

                val genreList = genreDao.loadAll()
                val moveGenreList = mutableListOf<MovieGenre>()
                response.body.movies.forEach { movie ->
                    moveGenreList.addAll(movie.genreIds.map {
                        MovieGenre(
                            movieId = movie.id,
                            genreId = it,
                            genreName = genreList.find { genre -> genre.id == it }?.name ?: String.empty
                        ) })
                }
                putMoviesGenres(movies, moveGenreList)

                UseCaseResult.Success(movies)
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
            it.map { movieEntity -> movieEntity.fromEntity() }
        }
    }

    override fun movie(movieId: Long): LiveData<Movie?> {
        return Transformations.map(movieDao.getById(movieId)) {
            it?.fromEntity()
        }
    }

    override fun movieInfo(movieId: Long): LiveData<MovieInfo?> {
        return Transformations.map(movieDao.getInfoById(movieId)) {
            it?.fromEntity()
        }
    }

    override fun movieGenres(movieId: Long): LiveData<List<MovieGenre>> {
        return Transformations.map(movieGenreDao.getByMovie(movieId)) {
            it.map { movieGenreEntity -> movieGenreEntity.fromEntity() }
        }
    }

    override fun movieCasts(movieId: Long): LiveData<List<MovieCast>> {
        return Transformations.map(movieCastDao.getByMovie(movieId)) {
            it.map { movieCastEntity -> movieCastEntity.fromEntity() }
        }
    }

    override fun movieCrews(movieId: Long): LiveData<List<MovieCrew>> {
        return Transformations.map(movieCrewDao.getByMovie(movieId)) {
            it.map { movieCrewEntity -> movieCrewEntity.fromEntity() }
        }
    }
}