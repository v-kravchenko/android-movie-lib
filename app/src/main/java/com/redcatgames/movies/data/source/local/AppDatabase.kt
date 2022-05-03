package com.redcatgames.movies.data.source.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.redcatgames.movies.data.source.local.converter.DateConverter
import com.redcatgames.movies.data.source.local.converter.IntListConverter
import com.redcatgames.movies.data.source.local.converter.LongListConverter
import com.redcatgames.movies.data.source.local.converter.StringListConverter
import com.redcatgames.movies.data.source.local.dao.CountryDao
import com.redcatgames.movies.data.source.local.dao.MovieDao
import com.redcatgames.movies.data.source.local.entity.CountryEntity
import com.redcatgames.movies.data.source.local.entity.MovieEntity

@Database(entities = [CountryEntity::class, MovieEntity::class],
    version = 4, exportSchema = false)
@TypeConverters(DateConverter::class, StringListConverter::class, IntListConverter::class, LongListConverter::class)
abstract class AppDatabase : RoomDatabase() {
    abstract val countryDao: CountryDao
    abstract val movieDao: MovieDao

    companion object {
        const val DB_NAME = "MoviesDatabase.db"
    }
}