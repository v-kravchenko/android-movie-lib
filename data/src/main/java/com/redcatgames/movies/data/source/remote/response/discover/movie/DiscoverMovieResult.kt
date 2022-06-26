package com.redcatgames.movies.data.source.remote.response.discover.movie

import com.google.gson.annotations.SerializedName

data class DiscoverMovieResult(
    @SerializedName("page") val page: Long,
    @SerializedName("total_pages") val pageCount: Long,
    @SerializedName("total_results") val movieCount: Long,
    @SerializedName("results") val movies: List<Movie>
) {
    data class Movie(
        @SerializedName("adult") val isAdult: Boolean,
        @SerializedName("backdrop_path") val backdropPath: String?,
        @SerializedName("genre_ids") val genreIds: List<Long>,
        @SerializedName("id") val id: Long,
        @SerializedName("original_language") val originalLanguage: String?,
        @SerializedName("original_title") val originalTitle: String?,
        @SerializedName("overview") val overview: String?,
        @SerializedName("popularity") val popularity: Double,
        @SerializedName("poster_path") val posterPath: String?,
        @SerializedName("release_date") val releaseDate: String?,
        @SerializedName("title") val title: String?,
        @SerializedName("video") val video: Boolean,
        @SerializedName("vote_average") val voteAverage: Double,
        @SerializedName("vote_count") val voteCount: Int
    )

}
