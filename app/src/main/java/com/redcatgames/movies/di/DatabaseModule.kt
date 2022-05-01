package com.redcatgames.movies.di

import android.app.Application
import androidx.room.Room
import androidx.room.RoomDatabase
import com.redcatgames.movies.data.source.local.AppDatabase
import com.redcatgames.movies.data.source.local.dao.ImageConfigDao
import com.redcatgames.movies.data.source.local.dao.MovieDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class DatabaseModule {

    @Provides
    @Singleton
    internal fun provideAppDatabase(application: Application): AppDatabase {
        return Room.databaseBuilder(
            application,
            AppDatabase::class.java,
            AppDatabase.DB_NAME
        )
            .fallbackToDestructiveMigration()
            //.allowMainThreadQueries()
            .setJournalMode(RoomDatabase.JournalMode.TRUNCATE)
            .build()
    }

    @Provides
    @Singleton
    fun provideImageConfigDao(appDatabase: AppDatabase): ImageConfigDao {
        return appDatabase.imageConfigDao
    }

    @Provides
    @Singleton
    fun provideMovieDao(appDatabase: AppDatabase): MovieDao {
        return appDatabase.movieDao
    }

}
