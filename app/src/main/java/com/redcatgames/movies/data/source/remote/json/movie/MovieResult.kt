package com.redcatgames.movies.data.source.remote.json.movie

import com.google.gson.annotations.SerializedName

data class MovieResult(
    @SerializedName("adult") val isAdult : Boolean,
    @SerializedName("backdrop_path") val backdropPath : String,
    @SerializedName("genres") val genres : List<Genre>,
    @SerializedName("id") val id : Long,
    @SerializedName("original_language") val originalLanguage : String,
    @SerializedName("original_title") val originalTitle : String,
    @SerializedName("overview") val overview : String,
    @SerializedName("popularity") val popularity : Double,
    @SerializedName("poster_path") val posterPath : String,
    @SerializedName("release_date") val releaseDate : String,
    @SerializedName("title") val title : String,
    @SerializedName("video") val video : Boolean,
    @SerializedName("vote_average") val voteAverage : Double,
    @SerializedName("vote_count") val voteCount : Int
) {
    data class Genre(
        @SerializedName("id") val id : Int,
        @SerializedName("name") val name: String
    )
}