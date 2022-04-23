package com.redcatgames.musiclib.di

import android.app.Application
import androidx.room.Room
import androidx.room.RoomDatabase
import com.redcatgames.musiclib.data.repository.ArtistRepositoryImpl
import com.redcatgames.musiclib.data.source.local.AppDatabase
import com.redcatgames.musiclib.data.source.local.dao.ArtistDao
import com.redcatgames.musiclib.domain.repository.ArtistRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class RepositoryModule {

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

    @Singleton
    @Provides
    fun provideArtistRepository(artistDao: ArtistDao): ArtistRepository {
        return ArtistRepositoryImpl(artistDao)
    }

}
