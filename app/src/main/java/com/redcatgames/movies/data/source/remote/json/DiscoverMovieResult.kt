package com.redcatgames.movies.data.source.remote.json

import com.google.gson.annotations.SerializedName

data class DiscoverMovieResult(
    @SerializedName("page") val page: Long,
    @SerializedName("total_pages") val pageCount: Long,
    @SerializedName("total_results") val movieCount: Long
)
