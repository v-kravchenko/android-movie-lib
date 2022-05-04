package com.redcatgames.movies.di

import com.redcatgames.movies.data.preferences.image.ImageConfigPreferences
import com.redcatgames.movies.data.repository.MovieRepositoryImpl
import com.redcatgames.movies.data.source.local.dao.*
import com.redcatgames.movies.data.source.remote.NetworkService
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
    fun provideMovieRepository(
        imageConfigPreferences: ImageConfigPreferences,
        countryDao: CountryDao,
        languageDao: LanguageDao,
        primaryTranslationDao: PrimaryTranslationDao,
        timezoneDao: TimezoneDao,
        genreDao: GenreDao,
        movieDao: MovieDao,
        networkService: NetworkService
    ): MovieRepository {
        return MovieRepositoryImpl(
            imageConfigPreferences,
            countryDao,
            languageDao,
            primaryTranslationDao,
            timezoneDao,
            genreDao,
            movieDao,
            networkService
        )
    }

}
