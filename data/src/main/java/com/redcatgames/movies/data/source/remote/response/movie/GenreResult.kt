package com.redcatgames.movies.data.source.remote.response.movie

import com.google.gson.annotations.SerializedName

data class GenreResult(
    @SerializedName("genres") val genres: List<Genre>,
) {
    data class Genre(@SerializedName("id") val id: Long, @SerializedName("name") val name: String?)
}
