package com.redcatgames.movies.di

import android.content.Context
import coil.ImageLoader
import com.redcatgames.movies.BuildConfig
import com.redcatgames.movies.data.preferences.ImageConfigPreferences
import com.redcatgames.movies.data.remote.interceptor.ImageHostSelectionInterceptor
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Named
import javax.inject.Singleton
import okhttp3.OkHttpClient

@InstallIn(SingletonComponent::class)
@Module
class ImageLoaderModule {

    @Singleton
    @Provides
    @Named("CoilHttp")
    fun provideHttpClient(imageConfigPreferences: ImageConfigPreferences): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(ImageHostSelectionInterceptor(imageConfigPreferences))
            .build()
    }

    @Provides
    @Singleton
    fun provideImageLoader(
        @ApplicationContext context: Context,
        @Named("CoilHttp") httpClient: OkHttpClient
    ): ImageLoader {
        val builder = ImageLoader.Builder(context).crossfade(true).okHttpClient(httpClient)

//        if (BuildConfig.DEBUG) {
//                        builder.diskCache(null)
//                        builder.memoryCache(null)
//        }

        return builder.build()
    }
}
