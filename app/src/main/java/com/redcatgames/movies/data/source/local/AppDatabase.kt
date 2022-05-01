package com.redcatgames.movies.data.source.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.redcatgames.movies.data.source.local.converter.DateConverter
import com.redcatgames.movies.data.source.local.converter.ListConverter
import com.redcatgames.movies.data.source.local.dao.ImageConfigDao
import com.redcatgames.movies.data.source.local.dao.MovieDao
import com.redcatgames.movies.data.source.local.entity.ImageConfigEntity
import com.redcatgames.movies.data.source.local.entity.MovieEntity

@Database(entities = [ImageConfigEntity::class, MovieEntity::class],
    version = 3, exportSchema = false)
@TypeConverters(DateConverter::class, ListConverter::class)
abstract class AppDatabase : RoomDatabase() {
    abstract val imageConfigDao: ImageConfigDao
    abstract val movieDao: MovieDao

    companion object {
        const val DB_NAME = "MoviesDatabase.db"
    }
}