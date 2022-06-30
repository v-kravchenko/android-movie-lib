package com.redcatgames.movies.data.remote.response.configuration

import com.google.gson.annotations.SerializedName

data class ConfigurationTimezonesResult(
    @SerializedName("iso_3166_1") val iso: String?,
    @SerializedName("zones") val zones: List<String>
)
