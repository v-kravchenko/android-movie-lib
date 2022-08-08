package com.redcatgames.movies.data.remote

import com.redcatgames.movies.data.remote.adapter.NetworkResponse
import com.redcatgames.movies.data.remote.response.BaseError
import com.redcatgames.movies.data.remote.response.configuration.ConfigurationCountriesResult
import com.redcatgames.movies.data.remote.response.configuration.ConfigurationLanguagesResult
import com.redcatgames.movies.data.remote.response.configuration.ConfigurationResult
import com.redcatgames.movies.data.remote.response.configuration.ConfigurationTimezonesResult
import com.redcatgames.movies.data.remote.response.discover.movie.DiscoverMovieResult
import com.redcatgames.movies.data.remote.response.movie.GenreResult
import com.redcatgames.movies.data.remote.response.movie.MovieResult
import com.redcatgames.movies.data.remote.response.movie.credits.MovieCreditsResult
import com.redcatgames.movies.data.remote.response.person.PersonResult
import com.redcatgames.movies.data.remote.response.person.credits.PersonCreditsResult
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface NetworkService {

    companion object {
        const val BASE_URL = "https://api.themoviedb.org/3/"
        const val TOKEN =
            "eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiJjNmQ3M2FhNzA5NWE3ZTcyMDI5MmFhOWRmNDNkMTM1ZCIsInN1YiI6IjYyNjE1ZjcyMTY4ZWEzMTU1N2NmNzIxYSIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.Q6tPWfntE0MSLXZ2zqaXJzqk-yDA_Ewt5w7VbtVEJ0o"
    }

    @GET("configuration")
    suspend fun getConfiguration(): NetworkResponse<ConfigurationResult, BaseError>

    @GET("configuration/countries")
    suspend fun getCountries(): NetworkResponse<List<ConfigurationCountriesResult>, BaseError>

    @GET("configuration/languages")
    suspend fun getLanguages(): NetworkResponse<List<ConfigurationLanguagesResult>, BaseError>

    @GET("configuration/primary_translations")
    suspend fun getPrimaryTranslations(): NetworkResponse<List<String>, BaseError>

    @GET("configuration/timezones")
    suspend fun getTimezones(): NetworkResponse<List<ConfigurationTimezonesResult>, BaseError>

    @GET("genre/movie/list")
    suspend fun getGenres(): NetworkResponse<GenreResult, BaseError>

    @GET("discover/movie?sort_by=popularity.desc")
    suspend fun getPopularMovies(
        @Query("page") page: Int
    ): NetworkResponse<DiscoverMovieResult, BaseError>

    @GET("discover/movie?sort_by=vote_count.desc")
    suspend fun getMostVotesMovies(
        @Query("page") page: Int
    ): NetworkResponse<DiscoverMovieResult, BaseError>

    @GET("movie/{movieId}")
    suspend fun getMovie(@Path("movieId") movieId: Long): NetworkResponse<MovieResult, BaseError>

    @GET("movie/{movieId}/credits")
    suspend fun getMovieCredits(
        @Path("movieId") movieId: Long
    ): NetworkResponse<MovieCreditsResult, BaseError>

    @GET("person/{personId}")
    suspend fun getPerson(
        @Path("personId") personId: Long
    ): NetworkResponse<PersonResult, BaseError>

    @GET("person/{personId}/movie_credits")
    suspend fun getPersonCredits(
        @Path("personId") personId: Long
    ): NetworkResponse<PersonCreditsResult, BaseError>
}
