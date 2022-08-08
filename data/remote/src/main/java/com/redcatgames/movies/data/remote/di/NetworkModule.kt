package com.redcatgames.movies.data.remote.di

import com.google.gson.*
import com.redcatgames.movies.data.preferences.UserConfigPreferences
import com.redcatgames.movies.data.remote.NetworkService
import com.redcatgames.movies.data.remote.adapter.NetworkResponseAdapterFactory
import com.redcatgames.movies.data.remote.interceptor.BearerLoginInterceptor
import com.redcatgames.movies.data.remote.interceptor.LanguageInterceptor
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.lang.reflect.Type
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Named
import javax.inject.Singleton


@InstallIn(SingletonComponent::class)
@Module
class NetworkModule {

    companion object {
        private const val TmdbDateFormat = "yyyy-MM-dd"
    }

    @Provides
    @Singleton
    @Named("TmdbDateFormat")
    fun provideDateFormat(locale: Locale) =
        SimpleDateFormat(TmdbDateFormat, locale)

    @Provides
    @Singleton
    fun provideGson(@Named("TmdbDateFormat") dateFormat: SimpleDateFormat): Gson = GsonBuilder()
        .setDateFormat(TmdbDateFormat)
        .registerTypeAdapter(Date::class.java, object : JsonDeserializer<Date> {
            override fun deserialize(
                json: JsonElement,
                typeOfT: Type?,
                context: JsonDeserializationContext?,
            ): Date? {
                return try {
                    dateFormat.parse(json.asString)
                } catch (e: ParseException) {
                    e.printStackTrace()
                    null
                }
            }
        })
        .create()

    @Singleton
    @Provides
    @Named("TMDBHttp")
    fun provideHttpClient(userConfigPreferences: UserConfigPreferences): OkHttpClient {

        val interceptor = HttpLoggingInterceptor()
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY)

        return OkHttpClient.Builder()
            .addInterceptor(interceptor)
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
