package com.redcatgames.movies.data.remote.response.configuration

import com.google.gson.annotations.SerializedName

data class ConfigurationLanguagesResult(
    @SerializedName("iso_639_1") val iso: String?,
    @SerializedName("english_name") val englishName: String?,
    @SerializedName("name") val name: String?
)
