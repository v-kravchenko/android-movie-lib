package com.redcatgames.movies.data.source.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.redcatgames.movies.data.source.local.converter.DateConverter
import com.redcatgames.movies.data.source.local.dao.ArtistDao
import com.redcatgames.movies.data.source.local.dao.MovieDao
import com.redcatgames.movies.data.source.local.entity.ArtistEntity
import com.redcatgames.movies.data.source.local.entity.MovieEntity

@Database(entities = [ArtistEntity::class, MovieEntity::class], version = 2, exportSchema = false)
@TypeConverters(DateConverter::class)
abstract class AppDatabase : RoomDatabase() {
    abstract val artistDao: ArtistDao
    abstract val movieDao: MovieDao

    companion object {
        const val DB_NAME = "MoviesDatabase.db"
    }
}