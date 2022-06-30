package com.redcatgames.movies.data.local.di

import android.app.Application
import androidx.room.Room
import androidx.room.RoomDatabase
import com.redcatgames.movies.data.local.AppDatabase
import com.redcatgames.movies.data.local.dao.*
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
        return Room.databaseBuilder(application, AppDatabase::class.java, AppDatabase.DB_NAME)
            .fallbackToDestructiveMigration()
            // .allowMainThreadQueries()
            .setJournalMode(RoomDatabase.JournalMode.TRUNCATE)
            .build()
    }

    @Provides
    @Singleton
    fun provideDictionaryDao(appDatabase: AppDatabase): DictionaryDao {
        return appDatabase.dictionaryDao
    }

    @Provides
    @Singleton
    fun provideCountryDao(appDatabase: AppDatabase): CountryDao {
        return appDatabase.countryDao
    }

    @Provides
    @Singleton
    fun provideLanguageDao(appDatabase: AppDatabase): LanguageDao {
        return appDatabase.languageDao
    }

    @Provides
    @Singleton
    fun providePrimaryTranslationDao(appDatabase: AppDatabase): PrimaryTranslationDao {
        return appDatabase.primaryTranslationDao
    }

    @Provides
    @Singleton
    fun provideTimeZoneDao(appDatabase: AppDatabase): TimezoneDao {
        return appDatabase.timezoneDao
    }

    @Provides
    @Singleton
    fun provideGenreDao(appDatabase: AppDatabase): GenreDao {
        return appDatabase.genreDao
    }

    @Provides
    @Singleton
    fun provideMovieDao(appDatabase: AppDatabase): MovieDao {
        return appDatabase.movieDao
    }

    @Provides
    @Singleton
    fun provideMovieGenreDao(appDatabase: AppDatabase): MovieGenreDao {
        return appDatabase.movieGenreDao
    }

    @Provides
    @Singleton
    fun provideMovieCastDao(appDatabase: AppDatabase): MovieCastDao {
        return appDatabase.movieCastDao
    }

    @Provides
    @Singleton
    fun provideMovieCrewDao(appDatabase: AppDatabase): MovieCrewDao {
        return appDatabase.movieCrewDao
    }

    @Provides
    @Singleton
    fun providePersonDao(appDatabase: AppDatabase): PersonDao {
        return appDatabase.personDao
    }
}
