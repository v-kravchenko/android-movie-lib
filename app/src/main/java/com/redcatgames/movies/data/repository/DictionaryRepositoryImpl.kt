package com.redcatgames.movies.data.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.redcatgames.movies.data.preferences.image.ImageConfigPreferences
import com.redcatgames.movies.data.preferences.image.UserConfigPreferences
import com.redcatgames.movies.data.source.local.dao.*
import com.redcatgames.movies.data.source.local.mapper.fromEntity
import com.redcatgames.movies.data.source.local.mapper.toEntity
import com.redcatgames.movies.data.source.remote.NetworkService
import com.redcatgames.movies.data.source.remote.adapter.NetworkResponse
import com.redcatgames.movies.data.source.remote.mapper.toCountry
import com.redcatgames.movies.data.source.remote.mapper.toLanguage
import com.redcatgames.movies.data.source.remote.mapper.toTimezoneList
import com.redcatgames.movies.data.source.remote.mapper.toGenre
import com.redcatgames.movies.data.source.remote.mapper.toImageConfig
import com.redcatgames.movies.domain.model.*
import com.redcatgames.movies.domain.repository.DictionaryRepository
import com.redcatgames.movies.domain.util.UseCaseResult
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.coroutineScope

class DictionaryRepositoryImpl(
    private val userConfigPreferences: UserConfigPreferences,
    private val imageConfigPreferences: ImageConfigPreferences,
    private val countryDao: CountryDao,
    private val languageDao: LanguageDao,
    private val primaryTranslationDao: PrimaryTranslationDao,
    private val timezoneDao: TimezoneDao,
    private val genreDao: GenreDao,
    private val networkService: NetworkService
) : DictionaryRepository {

    override suspend fun loadConfig(): UseCaseResult<Unit, String?> {
        return when (val response = networkService.getConfiguration()) {
            is NetworkResponse.Success -> {
                imageConfigPreferences.putConfig(response.body.images.toImageConfig())
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

    override suspend fun loadCountries(): UseCaseResult<Unit, String?> {
        return when (val response = networkService.getCountries()) {
            is NetworkResponse.Success -> {
                deleteAllCountries()
                putCountries(response.body.map { it.toCountry() })
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

    override suspend fun loadLanguages(): UseCaseResult<Unit, String?> {
        return when (val response = networkService.getLanguages()) {
            is NetworkResponse.Success -> {
                deleteAllLanguages()
                putLanguages(response.body.map { it.toLanguage() })
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

    override suspend fun loadPrimaryTranslations(): UseCaseResult<Unit, String?> {
        return when (val response = networkService.getPrimaryTranslations()) {
            is NetworkResponse.Success -> {
                deleteAllPrimaryTranslations()
                putPrimaryTranslations(response.body.map { PrimaryTranslation(it) })
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

    override suspend fun loadTimezones(): UseCaseResult<Unit, String?> {
        return when (val response = networkService.getTimezones()) {
            is NetworkResponse.Success -> {
                deleteAllTimezones()
                val timezones = response.body.flatMap { it.toTimezoneList() }
                putTimezones(timezones.toList())
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

    override suspend fun loadGenres(): UseCaseResult<Unit, String?> {
        return when (val response = networkService.getGenres()) {
            is NetworkResponse.Success -> {
                deleteAllGenres()
                putGenres(response.body.genres.map { it.toGenre() })
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

    override suspend fun loadDictionary(): UseCaseResult<Unit, String?> {
        return coroutineScope {
            val jobList= listOf(
                async { loadConfig() },
                async { loadCountries() },
                async { loadLanguages() },
                async { loadPrimaryTranslations() },
                async { loadTimezones() },
                async { loadGenres() }
                ).awaitAll()

            jobList.find { it.isFailure }?.let {
                if (it is UseCaseResult.Failure) {
                    return@coroutineScope UseCaseResult.Failure(it.error)
                }
            }

            return@coroutineScope UseCaseResult.Success<Unit, String?>(Unit)
        }
    }

    override suspend fun putCountries(countries: List<Country>) {
        countryDao.insertAll(countries.map { it.toEntity() })
    }

    override suspend fun putLanguages(languages: List<Language>) {
        languageDao.insertAll(languages.map { it.toEntity() })
    }

    override suspend fun putPrimaryTranslations(primaryTranslations: List<PrimaryTranslation>) {
        primaryTranslationDao.insertAll(primaryTranslations.map { it.toEntity() })
    }

    override suspend fun putTimezones(timezones: List<Timezone>) {
        timezoneDao.insertAll(timezones.map { it.toEntity() })
    }

    override suspend fun putGenres(genres: List<Genre>) {
        genreDao.insertAll(genres.map { it.toEntity() })
    }

    override suspend fun deleteAllCountries(): UseCaseResult<Int, Unit> {
        val rowCount = countryDao.getCount()
        countryDao.deleteAll()
        return UseCaseResult.Success(rowCount)
    }

    override suspend fun deleteAllLanguages(): UseCaseResult<Int, Unit> {
        val rowCount = languageDao.getCount()
        languageDao.deleteAll()
        return UseCaseResult.Success(rowCount)
    }

    override suspend fun deleteAllPrimaryTranslations(): UseCaseResult<Int, Unit> {
        val rowCount = primaryTranslationDao.getCount()
        primaryTranslationDao.deleteAll()
        return UseCaseResult.Success(rowCount)
    }

    override suspend fun deleteAllTimezones(): UseCaseResult<Int, Unit> {
        val rowCount = timezoneDao.getCount()
        timezoneDao.deleteAll()
        return UseCaseResult.Success(rowCount)
    }

    override suspend fun deleteAllGenres(): UseCaseResult<Int, Unit> {
        val rowCount = genreDao.getCount()
        genreDao.deleteAll()
        return UseCaseResult.Success(rowCount)
    }

    override suspend fun deleteAll() {
        coroutineScope {
            listOf(
                async { deleteAllCountries() },
                async { deleteAllLanguages() },
                async { deleteAllPrimaryTranslations() },
                async { deleteAllTimezones() },
                async { deleteAllGenres() }
            ).awaitAll()
        }
    }

    override fun userConfig(): LiveData<UserConfig> =
        userConfigPreferences.userConfig

    override fun imageConfig(): LiveData<ImageConfig> =
        imageConfigPreferences.imageConfig

    override fun languages(): LiveData<List<Language>> {
        return Transformations.map(languageDao.getAll()) {
            it.map { languageEntity -> languageEntity.fromEntity() }
        }
    }

    override fun getLanguage(iso: String): LiveData<Language?> {
        return Transformations.map(languageDao.getByIso(iso)) {
            it?.fromEntity()
        }
    }
}