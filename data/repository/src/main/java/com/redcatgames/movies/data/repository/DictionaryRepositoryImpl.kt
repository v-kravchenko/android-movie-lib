package com.redcatgames.movies.data.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.redcatgames.movies.data.local.dao.*
import com.redcatgames.movies.data.local.mapper.toDictionaryInfo
import com.redcatgames.movies.data.local.mapper.toEntity
import com.redcatgames.movies.data.local.mapper.toLanguage
import com.redcatgames.movies.data.preferences.image.ImageConfigPreferences
import com.redcatgames.movies.data.preferences.image.UserConfigPreferences
import com.redcatgames.movies.data.remote.NetworkService
import com.redcatgames.movies.data.remote.adapter.NetworkResponse
import com.redcatgames.movies.data.remote.mapper.*
import com.redcatgames.movies.domain.model.*
import com.redcatgames.movies.domain.repository.DictionaryRepository
import com.redcatgames.movies.util.now
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.coroutineScope

class DictionaryRepositoryImpl(
    private val userConfigPreferences: UserConfigPreferences,
    private val imageConfigPreferences: ImageConfigPreferences,
    private val dictionaryDao: DictionaryDao,
    private val countryDao: CountryDao,
    private val languageDao: LanguageDao,
    private val primaryTranslationDao: PrimaryTranslationDao,
    private val timezoneDao: TimezoneDao,
    private val genreDao: GenreDao,
    private val networkService: NetworkService
) : DictionaryRepository {

    override suspend fun loadConfig(): Result<Unit> {
        return when (val response = networkService.getConfiguration()) {
            is NetworkResponse.Success -> {
                imageConfigPreferences.putConfig(response.body.images.toImageConfig())
                Result.success(Unit)
            }
            is NetworkResponse.ApiError -> Result.failure(Exception(response.body.statusMessage))
            is NetworkResponse.NetworkError -> Result.failure(response.error)
            is NetworkResponse.UnknownError -> Result.failure(response.error)
        }
    }

    override suspend fun loadCountries(): Result<Unit> {
        return when (val response = networkService.getCountries()) {
            is NetworkResponse.Success -> {
                deleteAllCountries()
                putCountries(response.body.map { it.toCountry() })
                Result.success(Unit)
            }
            is NetworkResponse.ApiError -> Result.failure(Exception(response.body.statusMessage))
            is NetworkResponse.NetworkError -> Result.failure(response.error)
            is NetworkResponse.UnknownError -> Result.failure(response.error)
        }
    }

    override suspend fun loadLanguages(): Result<Unit> {
        return when (val response = networkService.getLanguages()) {
            is NetworkResponse.Success -> {
                deleteAllLanguages()
                putLanguages(response.body.map { it.toLanguage() })
                Result.success(Unit)
            }
            is NetworkResponse.ApiError -> Result.failure(Exception(response.body.statusMessage))
            is NetworkResponse.NetworkError -> Result.failure(response.error)
            is NetworkResponse.UnknownError -> Result.failure(response.error)
        }
    }

    override suspend fun loadPrimaryTranslations(): Result<Unit> {
        return when (val response = networkService.getPrimaryTranslations()) {
            is NetworkResponse.Success -> {
                deleteAllPrimaryTranslations()
                putPrimaryTranslations(response.body.map { PrimaryTranslation(it) })
                Result.success(Unit)
            }
            is NetworkResponse.ApiError -> Result.failure(Exception(response.body.statusMessage))
            is NetworkResponse.NetworkError -> Result.failure(response.error)
            is NetworkResponse.UnknownError -> Result.failure(response.error)
        }
    }

    override suspend fun loadTimezones(): Result<Unit> {
        return when (val response = networkService.getTimezones()) {
            is NetworkResponse.Success -> {
                deleteAllTimezones()
                val timezones = response.body.flatMap { it.toTimezoneList() }
                putTimezones(timezones.toList())
                Result.success(Unit)
            }
            is NetworkResponse.ApiError -> Result.failure(Exception(response.body.statusMessage))
            is NetworkResponse.NetworkError -> Result.failure(response.error)
            is NetworkResponse.UnknownError -> Result.failure(response.error)
        }
    }

    override suspend fun loadGenres(): Result<Unit> {
        return when (val response = networkService.getGenres()) {
            is NetworkResponse.Success -> {
                deleteAllGenres()
                putGenres(response.body.genres.map { it.toGenre() })
                Result.success(Unit)
            }
            is NetworkResponse.ApiError -> Result.failure(Exception(response.body.statusMessage))
            is NetworkResponse.NetworkError -> Result.failure(response.error)
            is NetworkResponse.UnknownError -> Result.failure(response.error)
        }
    }

    override suspend fun loadDictionary(): Result<Unit> = coroutineScope {
        deleteDictionaryInfo()

        val jobList =
            listOf(
                    async { loadConfig() },
                    async { loadCountries() },
                    async { loadLanguages() },
                    async { loadPrimaryTranslations() },
                    async { loadTimezones() },
                    async { loadGenres() }
                )
                .awaitAll()

        jobList.find { job -> job.isFailure }?.let {
            if (it.isFailure) {
                return@coroutineScope it
            }
        }

        val row = DictionaryInfo(getUserConfig().apiLanguage, now())
        putDictionaryInfo(row)

        return@coroutineScope Result.success(Unit)
    }

    override suspend fun putDictionaryInfo(dictionaryInfo: DictionaryInfo) {
        dictionaryDao.replace(dictionaryInfo.toEntity())
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

    override suspend fun deleteDictionaryInfo(): Result<Int> {
        val rowCount = dictionaryDao.getCount()
        dictionaryDao.deleteAll()
        return Result.success(rowCount)
    }

    override suspend fun deleteAllCountries(): Result<Int> {
        val rowCount = countryDao.getCount()
        countryDao.deleteAll()
        return Result.success(rowCount)
    }

    override suspend fun deleteAllLanguages(): Result<Int> {
        val rowCount = languageDao.getCount()
        languageDao.deleteAll()
        return Result.success(rowCount)
    }

    override suspend fun deleteAllPrimaryTranslations(): Result<Int> {
        val rowCount = primaryTranslationDao.getCount()
        primaryTranslationDao.deleteAll()
        return Result.success(rowCount)
    }

    override suspend fun deleteAllTimezones(): Result<Int> {
        val rowCount = timezoneDao.getCount()
        timezoneDao.deleteAll()
        return Result.success(rowCount)
    }

    override suspend fun deleteAllGenres(): Result<Int> {
        val rowCount = genreDao.getCount()
        genreDao.deleteAll()
        return Result.success(rowCount)
    }

    override suspend fun deleteAll() {
        coroutineScope {
            listOf(
                    async { deleteAllCountries() },
                    async { deleteAllLanguages() },
                    async { deleteAllPrimaryTranslations() },
                    async { deleteAllTimezones() },
                    async { deleteAllGenres() }
                )
                .awaitAll()
        }
    }

    override fun dictionaryInfo(): LiveData<DictionaryInfo?> =
        Transformations.map(dictionaryDao.first()) { it?.toDictionaryInfo() }

    override fun userConfig(): LiveData<UserConfig> = userConfigPreferences.userConfig

    override suspend fun getUserConfig(): UserConfig = userConfigPreferences.readConfig()

    override fun imageConfig(): LiveData<ImageConfig> = imageConfigPreferences.imageConfig

    override fun languages(): LiveData<List<Language>> =
        Transformations.map(languageDao.all()) {
            it.map { languageEntity -> languageEntity.toLanguage() }
        }

    override suspend fun getLanguage(iso: String): Language? =
        languageDao.getByIso(iso)?.toLanguage()

    override suspend fun putUserApiLanguage(language: Language) {
        val userConfig = userConfigPreferences.readConfig().copy(apiLanguage = language.iso)
        putConfig(userConfig)
    }

    override suspend fun putUserUiDarkMode(darkMode: Int) {
        val userConfig = userConfigPreferences.readConfig().copy(uiDarkMode = darkMode)
        putConfig(userConfig)
    }

    private suspend fun putConfig(userConfig: UserConfig) {
        userConfigPreferences.putConfig(userConfig)
    }
}
