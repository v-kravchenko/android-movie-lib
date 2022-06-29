package com.redcatgames.movies.data.repository.di

import com.redcatgames.movies.data.local.source.dao.*
import com.redcatgames.movies.data.preferences.source.image.ImageConfigPreferences
import com.redcatgames.movies.data.preferences.source.image.UserConfigPreferences
import com.redcatgames.movies.data.remote.source.NetworkService
import com.redcatgames.movies.data.repository.DictionaryRepositoryImpl
import com.redcatgames.movies.data.repository.MovieRepositoryImpl
import com.redcatgames.movies.domain.repository.DictionaryRepository
import com.redcatgames.movies.domain.repository.MovieRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class RepositoryModule {

    @Singleton
    @Provides
    fun provideDictionaryRepository(
        userConfigPreferences: UserConfigPreferences,
        imageConfigPreferences: ImageConfigPreferences,
        countryDao: CountryDao,
        languageDao: LanguageDao,
        primaryTranslationDao: PrimaryTranslationDao,
        timezoneDao: TimezoneDao,
        genreDao: GenreDao,
        networkService: NetworkService
    ): DictionaryRepository {
        return DictionaryRepositoryImpl(
            userConfigPreferences,
            imageConfigPreferences,
            countryDao,
            languageDao,
            primaryTranslationDao,
            timezoneDao,
            genreDao,
            networkService
        )
    }

    @Singleton
    @Provides
    fun provideMovieRepository(
        movieDao: MovieDao,
        movieGenreDao: MovieGenreDao,
        movieCastDao: MovieCastDao,
        movieCrewDao: MovieCrewDao,
        genreDao: GenreDao,
        networkService: NetworkService
    ): MovieRepository {
        return MovieRepositoryImpl(
            movieDao,
            movieGenreDao,
            movieCastDao,
            movieCrewDao,
            genreDao,
            networkService
        )
    }
}
