package com.redcatgames.movies.data.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.asFlow
import com.redcatgames.movies.data.local.dao.*
import com.redcatgames.movies.data.local.mapper.*
import com.redcatgames.movies.data.remote.NetworkService
import com.redcatgames.movies.data.remote.adapter.NetworkResponse
import com.redcatgames.movies.data.remote.mapper.*
import com.redcatgames.movies.domain.model.*
import com.redcatgames.movies.domain.repository.MovieRepository
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
    private val personDao: PersonDao,
    private val networkService: NetworkService
) : MovieRepository {

    override suspend fun deleteAllMovies(): Result<Int> {
        val rowCount = movieDao.getCount()
        movieDao.deleteAll()
        return Result.success(rowCount)
    }

    override suspend fun putMovies(movies: List<Movie>) {
        movieDao.insertAll(movies.map { it.toEntity() })
    }

    private suspend fun putMovieGenres(movie: Movie, genres: List<MovieGenre>) {
        val localGenres = movieGenres(movie.id).asFlow().first()
        if (localGenres.sortedBy { it.genreId } != genres.sortedBy { it.genreId }) {
            movieGenreDao.replace(movie.id, genres.map { it.toEntity() })
        }
    }

    private suspend fun putMovieCasts(movieId: Long, casts: List<MovieCast>) {
        val localCasts = movieCasts(movieId).asFlow().first()
        if (localCasts.sortedBy { it.id } != casts.sortedBy { it.id }) {
            movieCastDao.replace(movieId, casts.map { it.toEntity() })
        }
    }

    private suspend fun putMovieCrews(movieId: Long, crews: List<MovieCrew>) {
        val localCrews = movieCrews(movieId).asFlow().first()
        if (localCrews.sortedBy { it.id } != crews.sortedBy { it.id }) {
            movieCrewDao.replace(movieId, crews.map { it.toEntity() })
        }
    }

    private suspend fun putMoviesGenres(
        movies: List<Movie>,
        moviesGenres: MutableList<MovieGenre>
    ) {
        movieGenreDao.replace(movies.map { it.id }, moviesGenres.map { it.toEntity() })
    }

    override suspend fun putMovie(movie: Movie) {
        if (movie != movie(movie.id).asFlow().firstOrNull()) {
            movieDao.insert(movie.toEntity())
        }
    }

    override suspend fun putPerson(person: Person) {
        personDao.insert(person.toEntity())
    }

    override suspend fun loadMovieInfo(movieId: Long): Result<Unit> {
        return coroutineScope {
            val jobList =
                listOf(async { loadMovie(movieId) }, async { loadMovieCredits(movieId) }).awaitAll()

            jobList.find { it.isFailure }?.let {
                if (it.isFailure) {
                    return@coroutineScope it
                }
            }

            Result.success(Unit)
        }
    }

    override suspend fun loadMovieCredits(movieId: Long): Result<Unit> {
        return coroutineScope {
            when (val response = networkService.getMovieCredits(movieId)) {
                is NetworkResponse.Success -> {
                    listOf(
                            async {
                                val castList = response.body.toMovieCastList()
                                putMovieCasts(movieId, castList)
                            },
                            async {
                                val crewList = response.body.toMovieCrewList()
                                putMovieCrews(movieId, crewList)
                            }
                        )
                        .awaitAll()

                    Result.success(Unit)
                }
                is NetworkResponse.ApiError ->
                    Result.failure(Exception(response.body.statusMessage))
                is NetworkResponse.NetworkError -> Result.failure(response.error)
                is NetworkResponse.UnknownError -> Result.failure(response.error)
            }
        }
    }

    override suspend fun loadMovie(movieId: Long): Result<Unit> {

        return when (val response = networkService.getMovie(movieId)) {
            is NetworkResponse.Success -> {
                val movie = response.body.toMovie()
                val genres = response.body.genres.map { it.toMovieGenre(movie) }
                putMovie(movie)
                putMovieGenres(movie, genres)
                Result.success(Unit)
            }
            is NetworkResponse.ApiError -> Result.failure(Exception(response.body.statusMessage))
            is NetworkResponse.NetworkError -> Result.failure(response.error)
            is NetworkResponse.UnknownError -> Result.failure(response.error)
        }
    }

    override suspend fun loadPerson(personId: Long): Result<Unit> {
        return when (val response = networkService.getPerson(personId)) {
            is NetworkResponse.Success -> {
                val person = response.body.toPerson()
                putPerson(person)
                return Result.success(Unit)
            }
            is NetworkResponse.ApiError -> Result.failure(Exception(response.body.statusMessage))
            is NetworkResponse.NetworkError -> Result.failure(response.error)
            is NetworkResponse.UnknownError -> Result.failure(response.error)
        }
    }

    override suspend fun loadPopularMovies(page: Int): Result<List<Movie>> {

        return when (val response = networkService.getPopularMovies(page)) {
            is NetworkResponse.Success -> {

                val movies = response.body.movies.map { it.toMovie() }
                movieDao.replace(movies.map { it.toEntity() })

                val genreList = genreDao.getAll()
                val moveGenreList = mutableListOf<MovieGenre>()
                response.body.movies.forEach { movie ->
                    moveGenreList.addAll(
                        movie.genreIds.map {
                            MovieGenre(
                                movieId = movie.id,
                                genreId = it,
                                genreName = genreList.find { genre -> genre.id == it }?.name
                                        ?: String.empty
                            )
                        }
                    )
                }
                putMoviesGenres(movies, moveGenreList)

                Result.success(movies)
            }
            is NetworkResponse.ApiError -> Result.failure(Exception(response.body.statusMessage))
            is NetworkResponse.NetworkError -> Result.failure(response.error)
            is NetworkResponse.UnknownError -> Result.failure(response.error)
        }
    }

    override fun popularMovies(): LiveData<List<Movie>> {
        return Transformations.map(movieDao.popular()) {
            it.map { movieEntity -> movieEntity.toMovie() }
        }
    }

    override fun movie(movieId: Long): LiveData<Movie?> {
        return Transformations.map(movieDao.byId(movieId)) { it?.toMovie() }
    }

    override fun movieInfo(movieId: Long): LiveData<MovieInfo?> {
        return Transformations.map(movieDao.infoById(movieId)) { it?.fromEntity() }
    }

    override fun movieGenres(movieId: Long): LiveData<List<MovieGenre>> {
        return Transformations.map(movieGenreDao.byMovie(movieId)) {
            it.map { movieGenreEntity -> movieGenreEntity.toMovieGenre() }
        }
    }

    override fun movieCasts(movieId: Long): LiveData<List<MovieCast>> {
        return Transformations.map(movieCastDao.byMovie(movieId)) {
            it.map { movieCastEntity -> movieCastEntity.toMovieCast() }
        }
    }

    override fun movieCrews(movieId: Long): LiveData<List<MovieCrew>> {
        return Transformations.map(movieCrewDao.byMovie(movieId)) {
            it.map { movieCrewEntity -> movieCrewEntity.toMovieCrew() }
        }
    }
}
