package com.redcatgames.movies.domain.repository

import com.redcatgames.movies.domain.model.*
import kotlinx.coroutines.flow.Flow

interface DictionaryRepository {
    suspend fun loadConfig(): Result<Unit>
    suspend fun loadCountries(): Result<Unit>
    suspend fun loadLanguages(): Result<Unit>
    suspend fun loadPrimaryTranslations(): Result<Unit>
    suspend fun loadTimezones(): Result<Unit>
    suspend fun loadGenres(): Result<Unit>

    suspend fun loadDictionary(): Result<Unit>

    suspend fun putDictionaryInfo(dictionaryInfo: DictionaryInfo)
    suspend fun putCountries(countries: List<Country>)
    suspend fun putLanguages(languages: List<Language>)
    suspend fun putPrimaryTranslations(primaryTranslations: List<PrimaryTranslation>)
    suspend fun putTimezones(timezones: List<Timezone>)
    suspend fun putGenres(genres: List<Genre>)

    suspend fun deleteDictionaryInfo(): Result<Int>
    suspend fun deleteAllCountries(): Result<Int>
    suspend fun deleteAllLanguages(): Result<Int>
    suspend fun deleteAllPrimaryTranslations(): Result<Int>
    suspend fun deleteAllTimezones(): Result<Int>
    suspend fun deleteAllGenres(): Result<Int>
    suspend fun deleteAll()

    fun dictionaryInfo(): Flow<DictionaryInfo?>
    fun userConfig(): Flow<UserConfig>
    fun imageConfig(): Flow<ImageConfig>
    fun languages(): Flow<List<Language>>

    suspend fun getLanguage(iso: String): Language?

    suspend fun getUserConfig(): UserConfig
    suspend fun putUserApiLanguage(language: Language)

    suspend fun putUserUiDarkMode(darkMode: Int)
}
