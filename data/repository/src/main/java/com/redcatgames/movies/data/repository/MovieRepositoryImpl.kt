package com.redcatgames.movies.data.repository

import android.content.Context
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
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.map
import timber.log.Timber

class MovieRepositoryImpl(
    appContext: Context,
    private val networkService: NetworkService,
    private val movieDao: MovieDao,
    private val movieGenreDao: MovieGenreDao,
    private val movieCastDao: MovieCastDao,
    private val movieCrewDao: MovieCrewDao,
    private val genreDao: GenreDao,
    private val personDao: PersonDao,
    private val personCastDao: PersonCastDao,
    private val personCrewDao: PersonCrewDao,
) : MovieRepository, BaseRepository(appContext) {

    override suspend fun deleteAllMovies(): Result<Int> =
        withContext(Dispatchers.IO) {
            val rowCount = movieDao.getCount()
            movieDao.deleteAll()
            Result.success(rowCount)
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
        if (localCasts.sortedBy { it.personId } != casts.sortedBy { it.personId }) {
            movieCastDao.replace(movieId, casts.map { it.toEntity() })
        }
    }

    private suspend fun putMovieCrews(movieId: Long, crews: List<MovieCrew>) {
        val localCrews = movieCrews(movieId).asFlow().first()
        if (localCrews.sortedBy { it.personId } != crews.sortedBy { it.personId }) {
            movieCrewDao.replace(movieId, crews.map { it.toEntity() })
        }
    }

    private suspend fun putPersonCasts(personId: Long, casts: List<PersonCast>) {
        val localCasts = personCasts(personId).asFlow().first()
        if (localCasts.sortedBy { it.movieId } != casts.sortedBy { it.movieId }) {
            personCastDao.replace(personId, casts.map { it.toEntity() })
        }
    }

    private suspend fun putPersonCrews(personId: Long, crews: List<PersonCrew>) {
        val localCrews = personCrews(personId).asFlow().first()
        if (localCrews.sortedBy { it.movieId } != crews.sortedBy { it.movieId }) {
            personCrewDao.replace(personId, crews.map { it.toEntity() })
        }
    }

    private suspend fun putMoviesGenres(
        movies: List<Movie>,
        moviesGenres: MutableList<MovieGenre>,
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

    override suspend fun loadMovieInfo(movieId: Long): Result<Unit> =
        withContext(Dispatchers.IO) {
            coroutineScope {
                val jobList =
                    listOf(async { loadMovie(movieId) }, async { loadMovieCredits(movieId) })
                        .awaitAll()

                jobList.find { it.isFailure }?.let {
                    if (it.isFailure) {
                        return@coroutineScope it
                    }
                }

                Result.success(Unit)
            }
        }

    override suspend fun loadPersonCredits(personId: Long): Result<Unit> {
        return coroutineScope {
            when (val response = networkService.getPersonCredits(personId)) {
                is NetworkResponse.Success -> {
                    listOf(
                        async {
                            val castList = response.body.toPersonCastList()
                            putPersonCasts(personId, castList)
                        },
                        async {
                            val crewList = response.body.toPersonCrewList()
                            putPersonCrews(personId, crewList)
                        }
                    ).awaitAll()

                    Result.success(Unit)
                }
                is NetworkResponse.ApiError -> {
                    Result.failure(Exception(response.body.statusMessage))
                }
                is NetworkResponse.NetworkError -> {
                    Result.failure(response.error)
                }
                is NetworkResponse.UnknownError -> {
                    Result.failure(response.error)
                }
            }
        }
    }

    override suspend fun loadPersonInfo(personId: Long): Result<Unit> =
        withContext(Dispatchers.IO) {
            coroutineScope {

                val jobList =
                    listOf(async { loadPerson(personId) }, async { loadPersonCredits(personId) })
                        .awaitAll()

                jobList.find { it.isFailure }?.let {
                    if (it.isFailure) {
                        Timber.e(it.exceptionOrNull(), "loadPersonInfo($personId) ERROR")
                        return@coroutineScope it
                    }
                }

                Timber.d("loadPersonInfo($personId) OK")

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

    override suspend fun loadPopularMovies(): Result<List<Movie>> =
        withContext(Dispatchers.IO) {
            when (val response = networkService.getPopularMovies(1)) {
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
                is NetworkResponse.ApiError ->
                    Result.failure(Exception(response.body.statusMessage))
                is NetworkResponse.NetworkError -> Result.failure(response.error)
                is NetworkResponse.UnknownError -> Result.failure(response.error)
            }
        }

    override suspend fun loadMostVotesMovies(): Result<List<Movie>> =
        withContext(Dispatchers.IO) {
            when (val response = networkService.getMostVotesMovies(1)) {
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
                is NetworkResponse.ApiError ->
                    Result.failure(Exception(response.body.statusMessage))
                is NetworkResponse.NetworkError -> Result.failure(response.error)
                is NetworkResponse.UnknownError -> Result.failure(response.error)
            }
        }

    override fun popularMovies(): LiveData<List<Movie>> {
        return Transformations.map(movieDao.popular()) {
            it.map { movieEntity -> movieEntity.toMovie() }
        }
    }

    override fun popularMoviesFlow() =
        movieDao.popularFlow().map { it.map { entity -> entity.toMovie() } }

    override fun mostVotesMovies(): LiveData<List<Movie>> {
        return Transformations.map(movieDao.mostVotes()) {
            it.map { movieEntity -> movieEntity.toMovie() }
        }
    }

    override fun movie(movieId: Long): LiveData<Movie?> {
        return Transformations.map(movieDao.byId(movieId)) { it?.toMovie() }
    }

    override fun movieInfo(movieId: Long): LiveData<MovieInfo?> {
        return Transformations.map(movieDao.infoById(movieId)) {
            it?.fromEntity()
        }
    }

    override fun personInfo(personId: Long): LiveData<PersonInfo?> {
        return Transformations.map(personDao.infoById(personId)) {
            it?.fromEntity()
        }
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

    override fun person(personId: Long): LiveData<Person?> {
        return Transformations.map(personDao.byId(personId)) { it?.toPerson() }
    }

    override fun personCasts(personId: Long): LiveData<List<PersonCast>> {
        return Transformations.map(personCastDao.byPerson(personId)) {
            it.map { personCastEntity -> personCastEntity.toPersonCast() }
        }
    }

    override fun personCrews(personId: Long): LiveData<List<PersonCrew>> {
        return Transformations.map(personCrewDao.byPerson(personId)) {
            it.map { personCrewEntity -> personCrewEntity.toPersonCrew() }
        }
    }
}
