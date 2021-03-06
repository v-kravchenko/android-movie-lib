package com.redcatgames.movies.data.remote.di

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.redcatgames.movies.data.preferences.UserConfigPreferences
import com.redcatgames.movies.data.remote.NetworkService
import com.redcatgames.movies.data.remote.adapter.NetworkResponseAdapterFactory
import com.redcatgames.movies.data.remote.interceptor.BearerLoginInterceptor
import com.redcatgames.movies.data.remote.interceptor.LanguageInterceptor
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Named
import javax.inject.Singleton
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@InstallIn(SingletonComponent::class)
@Module
class NetworkModule {

    companion object {
        private const val TmdbDateFormat = "YYYY-MM-DD"
    }

    @Provides fun provideGson(): Gson = GsonBuilder()
        .setDateFormat(TmdbDateFormat)
        .create()

    @Singleton
    @Provides
    @Named("TMDBHttp")
    fun provideHttpClient(userConfigPreferences: UserConfigPreferences): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(BearerLoginInterceptor(NetworkService.TOKEN))
            .addInterceptor(LanguageInterceptor(userConfigPreferences))
            .build()
    }

    @Singleton
    @Provides
    @Named("TMDB")
    fun provideRetrofit(@Named("TMDBHttp") httpClient: OkHttpClient, gson: Gson): Retrofit {

        return Retrofit.Builder()
            .baseUrl(NetworkService.BASE_URL)
            .client(httpClient)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .addCallAdapterFactory(NetworkResponseAdapterFactory())
            .build()
    }

    @Singleton
    @Provides
    fun provideNetworkService(@Named("TMDB") retrofit: Retrofit): NetworkService {
        return retrofit.create(NetworkService::class.java)
    }
}
