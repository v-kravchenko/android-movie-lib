package com.redcatgames.movies.data.remote.response.person

import com.google.gson.annotations.SerializedName
import java.util.*

data class PersonResult(
    @SerializedName("adult") val isAdult: Boolean,
    @SerializedName("also_known_as") val alsoKnownAs: List<String>,
    @SerializedName("biography") val biography: String,
    @SerializedName("birthday") val birthDay: Date?,
    @SerializedName("deathday") val deathDay: Date?,
    @SerializedName("gender") val gender: Int,
    @SerializedName("homepage") val homepage: String?,
    @SerializedName("id") val id: Long,
    @SerializedName("imdb_id") val imdbId: String?,
    @SerializedName("known_for_department") val knownForDepartment: String,
    @SerializedName("name") val name: String,
    @SerializedName("place_of_birth") val placeOfBirth: String?,
    @SerializedName("popularity") val popularity: Double,
    @SerializedName("profile_path") val profilePath: String?
)
