package com.redcatgames.movies.data.source.remote

import com.redcatgames.movies.data.source.remote.adapter.NetworkResponse
import com.redcatgames.movies.data.source.remote.json.DiscoverMovieError
import com.redcatgames.movies.data.source.remote.json.DiscoverMovieResult
import retrofit2.Call
import retrofit2.http.GET

interface NetworkService {

    companion object {
        const val BASE_URL = "https://api.themoviedb.org/3/"
        const val TOKEN = "1eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiJjNmQ3M2FhNzA5NWE3ZTcyMDI5MmFhOWRmNDNkMTM1ZCIsInN1YiI6IjYyNjE1ZjcyMTY4ZWEzMTU1N2NmNzIxYSIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.Q6tPWfntE0MSLXZ2zqaXJzqk-yDA_Ewt5w7VbtVEJ0o"
    }

    @GET("discover/movie?sort_by=popularity.desc")
    suspend fun getPopularMovies(): NetworkResponse<DiscoverMovieResult, DiscoverMovieError>
}