package com.redcatgames.musiclib.di

import com.redcatgames.musiclib.data.repository.ArtistRepositoryImpl
import com.redcatgames.musiclib.domain.repository.ArtistRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class DataModule {

    @Singleton
    @Provides
    fun provideArtistRepository(): ArtistRepository {
        return ArtistRepositoryImpl()
    }

}
