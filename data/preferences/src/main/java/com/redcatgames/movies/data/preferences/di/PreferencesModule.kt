package com.redcatgames.movies.data.preferences.di

import android.content.Context
import com.redcatgames.movies.data.preferences.Preferences
import com.redcatgames.movies.data.preferences.image.ImageConfigPreferences
import com.redcatgames.movies.data.preferences.image.UserConfigPreferences
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class PreferencesModule {

    @Singleton
    @Provides
    fun providePreferences(@ApplicationContext appContext: Context): Preferences {
        return Preferences(context = appContext)
    }

    @Singleton
    @Provides
    fun provideImageConfigPreferences(preferences: Preferences): ImageConfigPreferences {
        return ImageConfigPreferences(preferences)
    }

    @Singleton
    @Provides
    fun provideUserConfigPreferences(preferences: Preferences): UserConfigPreferences {
        return UserConfigPreferences(preferences)
    }
}
