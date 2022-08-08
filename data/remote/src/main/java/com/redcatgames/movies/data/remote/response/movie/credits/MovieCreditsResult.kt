package com.redcatgames.movies.data.remote.response.movie.credits

import com.google.gson.annotations.SerializedName

data class MovieCreditsResult(
    @SerializedName("id") val movieId: Long,
    @SerializedName("cast") val castList: List<Cast>,
    @SerializedName("crew") val crewList: List<Crew>
) {
    data class Cast(
        @SerializedName("credit_id") val creditId: String,
        @SerializedName("id") val personId: Long,
        @SerializedName("adult") val isAdult: Boolean,
        @SerializedName("gender") val gender: Int,
        @SerializedName("known_for_department") val knownForDepartment: String?,
        @SerializedName("name") val name: String?,
        @SerializedName("original_name") val originalName: String?,
        @SerializedName("popularity") val popularity: Double,
        @SerializedName("profile_path") val profilePath: String?,
        @SerializedName("cast_id") val castId: Long,
        @SerializedName("character") val character: String?,
        @SerializedName("order") val order: Int
    )
    data class Crew(
        @SerializedName("credit_id") val creditId: String,
        @SerializedName("id") val personId: Long,
        @SerializedName("adult") val isAdult: Boolean,
        @SerializedName("gender") val gender: Int,
        @SerializedName("known_for_department") val knownForDepartment: String?,
        @SerializedName("name") val name: String?,
        @SerializedName("original_name") val originalName: String?,
        @SerializedName("popularity") val popularity: Double,
        @SerializedName("profile_path") val profilePath: String?,
        @SerializedName("department") val department: String?,
        @SerializedName("job") val job: String
    )
}
