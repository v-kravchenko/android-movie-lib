package com.redcatgames.movies.domain.model

import com.redcatgames.movies.util.format
import java.util.*

data class Movie(
    val id: Long,
    val isAdult: Boolean,
    val backdropPath: String?,
    val genreIds: List<Long>,
    val originalLanguage: String,
    val originalTitle: String,
    val overview: String,
    val popularity: Double,
    val posterPath: String,
    val releaseDate: Date?,
    val title: String,
    val video: Boolean,
    val voteAverage: Double,
    val voteCount: Int
) {
    val voteRating: String
        get() = if (voteAverage > 0) voteAverage.format(1).replace(',', '.') else "n/a"
}
