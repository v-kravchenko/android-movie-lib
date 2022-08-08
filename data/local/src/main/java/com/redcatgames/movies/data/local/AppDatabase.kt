package com.redcatgames.movies.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.redcatgames.movies.data.local.converter.DateConverter
import com.redcatgames.movies.data.local.converter.IntListConverter
import com.redcatgames.movies.data.local.converter.LongListConverter
import com.redcatgames.movies.data.local.converter.StringListConverter
import com.redcatgames.movies.data.local.dao.*
import com.redcatgames.movies.data.local.entity.*

@Database(
    entities =
    [
        DictionaryEntity::class,
        CountryEntity::class,
        LanguageEntity::class,
        PrimaryTranslationEntity::class,
        TimezoneEntity::class,
        GenreEntity::class,
        MovieEntity::class,
        MovieGenreEntity::class,
        MovieCastEntity::class,
        MovieCrewEntity::class,
        PersonEntity::class,
        PersonCastEntity::class,
        PersonCrewEntity::class],
    version = 2,
    exportSchema = false
)
@TypeConverters(
    DateConverter::class,
    StringListConverter::class,
    IntListConverter::class,
    LongListConverter::class
)
abstract class AppDatabase : RoomDatabase() {
    abstract val dictionaryDao: DictionaryDao
    abstract val countryDao: CountryDao
    abstract val languageDao: LanguageDao
    abstract val primaryTranslationDao: PrimaryTranslationDao
    abstract val timezoneDao: TimezoneDao
    abstract val genreDao: GenreDao
    abstract val movieDao: MovieDao
    abstract val movieGenreDao: MovieGenreDao
    abstract val movieCastDao: MovieCastDao
    abstract val movieCrewDao: MovieCrewDao
    abstract val personDao: PersonDao
    abstract val personCastDao: PersonCastDao
    abstract val personCrewDao: PersonCrewDao

    companion object {
        const val DB_NAME = "MoviesDatabase.db"
    }
}
