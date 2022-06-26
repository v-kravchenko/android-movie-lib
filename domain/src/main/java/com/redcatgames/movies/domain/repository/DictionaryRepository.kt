package com.redcatgames.movies.domain.repository

import androidx.lifecycle.LiveData
import com.redcatgames.movies.domain.model.*

interface DictionaryRepository {
    suspend fun loadConfig(): Result<Unit>
    suspend fun loadCountries(): Result<Unit>
    suspend fun loadLanguages(): Result<Unit>
    suspend fun loadPrimaryTranslations(): Result<Unit>
    suspend fun loadTimezones(): Result<Unit>
    suspend fun loadGenres(): Result<Unit>

    suspend fun loadDictionary(): Result<Unit>

    suspend fun putCountries(countries: List<Country>)
    suspend fun putLanguages(languages: List<Language>)
    suspend fun putPrimaryTranslations(primaryTranslations: List<PrimaryTranslation>)
    suspend fun putTimezones(timezones: List<Timezone>)
    suspend fun putGenres(genres: List<Genre>)

    suspend fun deleteAllCountries(): Result<Int>
    suspend fun deleteAllLanguages(): Result<Int>
    suspend fun deleteAllPrimaryTranslations(): Result<Int>
    suspend fun deleteAllTimezones(): Result<Int>
    suspend fun deleteAllGenres(): Result<Int>
    suspend fun deleteAll()

    fun userConfig(): LiveData<UserConfig>
    fun imageConfig(): LiveData<ImageConfig>
    fun languages(): LiveData<List<Language>>

    fun getLanguage(iso: String): LiveData<Language?>
    suspend fun putUserApiLanguage(language: Language)
}
