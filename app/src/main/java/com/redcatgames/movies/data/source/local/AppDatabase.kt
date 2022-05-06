package com.redcatgames.movies.data.source.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.redcatgames.movies.data.source.local.converter.DateConverter
import com.redcatgames.movies.data.source.local.converter.IntListConverter
import com.redcatgames.movies.data.source.local.converter.LongListConverter
import com.redcatgames.movies.data.source.local.converter.StringListConverter
import com.redcatgames.movies.data.source.local.dao.*
import com.redcatgames.movies.data.source.local.entity.*

@Database(entities = [
    CountryEntity::class, LanguageEntity::class, PrimaryTranslationEntity::class,
    TimezoneEntity::class, GenreEntity::class, MovieEntity::class, MovieGenreEntity::class,
    MovieCastEntity::class, MovieCrewEntity::class],
    version = 3, exportSchema = false)
@TypeConverters(DateConverter::class, StringListConverter::class, IntListConverter::class, LongListConverter::class)
abstract class AppDatabase : RoomDatabase() {
    abstract val countryDao: CountryDao
    abstract val languageDao: LanguageDao
    abstract val primaryTranslationDao: PrimaryTranslationDao
    abstract val timezoneDao: TimezoneDao
    abstract val genreDao: GenreDao
    abstract val movieDao: MovieDao
    abstract val movieGenreDao: MovieGenreDao
    abstract val movieCastDao: MovieCastDao
    abstract val movieCrewDao: MovieCrewDao

    companion object {
        const val DB_NAME = "MoviesDatabase.db"
    }
}