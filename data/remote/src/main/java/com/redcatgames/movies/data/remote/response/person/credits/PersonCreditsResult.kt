package com.redcatgames.movies.data.remote.response.person.credits

import com.google.gson.annotations.SerializedName
import java.util.*

data class PersonCreditsResult(
    @SerializedName("id") val personId: Long,
    @SerializedName("cast") val castList: List<Cast>,
    @SerializedName("crew") val crewList: List<Crew>
) {
    data class Cast(
        @SerializedName("credit_id") val creditId: String,
        @SerializedName("adult") val isAdult: Boolean,
        @SerializedName("backdrop_path") val backdropPath: String?,
        @SerializedName("genre_ids") val genreIds: List<Long>,
        @SerializedName("id") val movieId: Long,
        @SerializedName("original_language") val originalLanguage: String?,
        @SerializedName("original_title") val originalTitle: String?,
        @SerializedName("overview") val overview: String?,
        @SerializedName("popularity") val popularity: Double,
        @SerializedName("poster_path") val posterPath: String?,
        @SerializedName("release_date") val releaseDate: Date?,
        @SerializedName("title") val title: String?,
        @SerializedName("video") val isVideo: Boolean,
        @SerializedName("vote_average") val voteAverage: Double,
        @SerializedName("vote_count") val voteCount: Int,
        @SerializedName("character") val character: String?,
        @SerializedName("order") val order: Int
    )

    data class Crew(
        @SerializedName("credit_id") val creditId: String,
        @SerializedName("adult") val isAdult: Boolean,
        @SerializedName("backdrop_path") val backdropPath: String?,
        @SerializedName("genre_ids") val genreIds: List<Long>,
        @SerializedName("id") val movieId: Long,
        @SerializedName("original_language") val originalLanguage: String?,
        @SerializedName("original_title") val originalTitle: String?,
        @SerializedName("overview") val overview: String?,
        @SerializedName("popularity") val popularity: Double,
        @SerializedName("poster_path") val posterPath: String?,
        @SerializedName("release_date") val releaseDate: Date?,
        @SerializedName("title") val title: String?,
        @SerializedName("video") val isVideo: Boolean,
        @SerializedName("vote_average") val voteAverage: Double,
        @SerializedName("vote_count") val voteCount: Int,
        @SerializedName("department") val department: String?,
        @SerializedName("job") val job: String?
    )
}
