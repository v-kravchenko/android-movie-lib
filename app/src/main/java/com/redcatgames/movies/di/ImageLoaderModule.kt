package com.redcatgames.movies.di

import android.content.Context
import coil.ImageLoader
import com.redcatgames.movies.data.source.remote.NetworkService
import com.redcatgames.movies.data.source.remote.interceptor.BearerLoginInterceptor
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import javax.inject.Named
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class ImageLoaderModule {

    @Singleton
    @Provides
    @Named("CoilHttp")
    fun provideHttpClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(BearerLoginInterceptor(NetworkService.TOKEN))
            .build()
    }

    @Provides
    @Singleton
    fun provideImageLoader(
        @ApplicationContext context: Context,
        @Named("CoilHttp") httpClient: OkHttpClient
    ): ImageLoader {
        return ImageLoader
            .Builder(context)
            .okHttpClient(httpClient)
            .build()
    }

}
