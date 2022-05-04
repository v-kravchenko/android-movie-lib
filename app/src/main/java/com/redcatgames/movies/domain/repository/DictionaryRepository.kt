package com.redcatgames.movies.domain.repository

import androidx.lifecycle.LiveData
import com.redcatgames.movies.domain.model.*
import com.redcatgames.movies.domain.util.UseCaseResult

interface DictionaryRepository {
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

    suspend fun deleteAllCountries(): UseCaseResult<Int, Unit>
    suspend fun deleteAllLanguages(): UseCaseResult<Int, Unit>
    suspend fun deleteAllPrimaryTranslations(): UseCaseResult<Int, Unit>
    suspend fun deleteAllTimezones(): UseCaseResult<Int, Unit>
    suspend fun deleteAllGenres(): UseCaseResult<Int, Unit>

    fun userConfig(): LiveData<UserConfig>
    fun imageConfig(): LiveData<ImageConfig>
    fun languages(): LiveData<List<Language>>

    fun getLanguage(iso: String): LiveData<Language?>
}