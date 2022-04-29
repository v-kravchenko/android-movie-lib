package com.redcatgames.movies.data.source.remote

import retrofit2.http.GET

interface NetworkService {

    @GET("albums/")
    fun getAlbums(): String
}