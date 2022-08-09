package com.redcatgames.device.di

import android.content.Context
import android.os.Build
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import java.util.*
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class DeviceModule {

    @Provides
    @Singleton
    @Suppress("DEPRECATION")
    fun provideLocale(@ApplicationContext appContext: Context): Locale {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            appContext.resources.configuration.locales.get(0)
        } else {
            appContext.resources.configuration.locale
        }
    }
}
