package com.redcatgames.movies.domain.repository

import androidx.lifecycle.LiveData
import com.redcatgames.movies.domain.model.*
import com.redcatgames.movies.domain.util.UseCaseResult

interface MovieRepository {
    suspend fun loadConfig(): UseCaseResult<Unit, String?>
    suspend fun loadCountries(): UseCaseResult<Unit, String?>
    suspend fun loadLanguages(): UseCaseResult<Unit, String?>
    suspend fun loadPrimaryTranslations(): UseCaseResult<Unit, String?>
    suspend fun loadTimezones(): UseCaseResult<Unit, String?>
    suspend fun loadGenres(): UseCaseResult<Unit, String?>

    suspend fun loadDictionary(): UseCaseResult<Unit, String?>

    suspend fun putCountries(countries: List<Country>)
    suspend fun putLanguages(languages: List<Language>)
    suspend fun putPrimaryTranslations(primaryTranslations: List<PrimaryTranslation>)
    suspend fun putTimezones(timezones: List<Timezone>)
    suspend fun putGenres(genres: List<Genre>)

//    suspend fun putImageConfig(imageConfig: ImageConfig)
    fun imageConfig(): LiveData<ImageConfig>

    suspend fun deleteAllCountries(): UseCaseResult<Int, Unit>
    suspend fun deleteAllLanguages(): UseCaseResult<Int, Unit>
    suspend fun deleteAllPrimaryTranslations(): UseCaseResult<Int, Unit>
    suspend fun deleteAllTimezones(): UseCaseResult<Int, Unit>
    suspend fun deleteAllGenres(): UseCaseResult<Int, Unit>
    suspend fun deleteAllMovies(): UseCaseResult<Int, Unit>

    suspend fun putMovie(movie: Movie)
    suspend fun putMovies(movies: List<Movie>)
    suspend fun loadPopularMovies(page: Int): UseCaseResult<List<Movie>, String?>
    suspend fun loadMovie(movieId: Long): UseCaseResult<Unit, String?>
    fun popularMovies(): LiveData<List<Movie>>
    fun movie(movieId: Long): LiveData<Movie?>
}