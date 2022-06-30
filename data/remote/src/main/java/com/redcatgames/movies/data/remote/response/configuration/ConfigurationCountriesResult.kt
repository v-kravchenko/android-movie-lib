package com.redcatgames.movies.data.remote.response.configuration

import com.google.gson.annotations.SerializedName

data class ConfigurationCountriesResult(
    @SerializedName("iso_3166_1") val iso: String?,
    @SerializedName("english_name") val englishName: String?,
    @SerializedName("native_name") val nativeName: String?
)
