package com.redcatgames.movies.di

import android.content.Context
import com.redcatgames.movies.data.preferences.Preferences
import com.redcatgames.movies.data.repository.MovieRepositoryImpl
import com.redcatgames.movies.data.source.local.dao.ImageConfigDao
import com.redcatgames.movies.data.source.local.dao.MovieDao
import com.redcatgames.movies.data.source.remote.NetworkService
import com.redcatgames.movies.domain.repository.MovieRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class RepositoryModule {

    @Singleton
    @Provides
    fun provideMovieRepository(
        preferences: Preferences,
        imageConfigDao: ImageConfigDao,
        movieDao: MovieDao,
        networkService: NetworkService
    ): MovieRepository {
        return MovieRepositoryImpl(preferences, imageConfigDao, movieDao, networkService)
    }

    @Singleton
    @Provides
    fun providePreferences(
        @ApplicationContext appContext: Context
    ): Preferences {
        return Preferences(context = appContext)
    }

}
