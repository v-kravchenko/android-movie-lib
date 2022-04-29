package com.redcatgames.movies.data.source.remote

import com.redcatgames.movies.data.source.remote.json.DiscoverMovieResult
import retrofit2.Response
import retrofit2.http.GET

interface NetworkService {

    companion object {
        const val BASE_URL = "https://api.themoviedb.org/3/"
    }

    @GET("discover/movie?sort_by=popularity.desc")
    fun getPopularMovies(): Response<DiscoverMovieResult>
}