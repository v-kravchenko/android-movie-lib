package com.redcatgames.movies.data.source.remote.json

import com.google.gson.annotations.SerializedName

data class DiscoverMovieError(
    @SerializedName("status_code") val statusCode: String,
    @SerializedName("status_message") val statusMessage: String
)
