package com.redcatgames.movies.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class BaseError(
    @SerializedName("status_code") val statusCode: String,
    @SerializedName("status_message") val statusMessage: String
)
