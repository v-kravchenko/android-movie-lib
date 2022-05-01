package com.redcatgames.movies.data.source.remote

import com.redcatgames.movies.data.source.remote.adapter.NetworkResponse
import com.redcatgames.movies.data.source.remote.json.BaseError
import com.redcatgames.movies.data.source.remote.json.discover.movie.DiscoverMovieResult
import com.redcatgames.movies.data.source.remote.json.movie.MovieResult
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface NetworkService {

    companion object {
        const val BASE_URL = "https://api.themoviedb.org/3/"
        const val TOKEN = "eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiJjNmQ3M2FhNzA5NWE3ZTcyMDI5MmFhOWRmNDNkMTM1ZCIsInN1YiI6IjYyNjE1ZjcyMTY4ZWEzMTU1N2NmNzIxYSIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.Q6tPWfntE0MSLXZ2zqaXJzqk-yDA_Ewt5w7VbtVEJ0o"
    }

    @GET("discover/movie?sort_by=popularity.desc")
    suspend fun getPopularMovies(@Query("page") page: Int):
            NetworkResponse<DiscoverMovieResult, BaseError>

    @GET("movie/{movieId}")
    suspend fun getMovie(@Path("movieId") movieId: Long):
            NetworkResponse<MovieResult, BaseError>
}