package com.redcatgames.movies.di

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.redcatgames.movies.data.source.remote.NetworkService
import com.redcatgames.movies.data.source.remote.adapter.NetworkResponseAdapterFactory
import com.redcatgames.movies.data.source.remote.interceptor.BearerLoginInterceptor
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Named
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class NetworkModule {

    @Provides
    fun provideGson(): Gson = GsonBuilder().create()

    @Singleton
    @Provides
    fun provideNetworkService(@Named("TMDB") retrofit: Retrofit): NetworkService {
        return retrofit.create(NetworkService::class.java)
    }

    @Singleton
    @Provides
    @Named("TMDB")
    fun provideRetrofit(gson: Gson): Retrofit {

        val client = OkHttpClient.Builder()
            .addInterceptor(BearerLoginInterceptor(NetworkService.TOKEN))
            .build()

        return Retrofit.Builder()
            .baseUrl(NetworkService.BASE_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .addCallAdapterFactory(NetworkResponseAdapterFactory())
            .build()
    }

}