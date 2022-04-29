package com.redcatgames.movies.data.source.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.redcatgames.movies.data.source.local.converter.DateConverter
import com.redcatgames.movies.data.source.local.dao.ArtistDao
import com.redcatgames.movies.data.source.local.entity.ArtistEntity

@Database(entities = [ArtistEntity::class], version = 1, exportSchema = false)
@TypeConverters(DateConverter::class)
abstract class AppDatabase : RoomDatabase() {
    abstract val artistDao: ArtistDao

    companion object {
        const val DB_NAME = "Movies.db"
    }
}