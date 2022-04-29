package com.redcatgames.movies.di

import android.app.Application
import androidx.room.Room
import androidx.room.RoomDatabase
import com.redcatgames.movies.data.repository.ArtistRepositoryImpl
import com.redcatgames.movies.data.source.local.AppDatabase
import com.redcatgames.movies.data.source.local.dao.ArtistDao
import com.redcatgames.movies.data.source.local.dao.MovieDao
import com.redcatgames.movies.domain.repository.ArtistRepository
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
    internal fun provideArtistDao(appDatabase: AppDatabase): ArtistDao {
        return appDatabase.artistDao
    }

    @Provides
    @Singleton
    fun provideMovieDao(appDatabase: AppDatabase): MovieDao {
        return appDatabase.movieDao
    }

}
