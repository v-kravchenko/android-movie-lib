package com.redcatgames.movies.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import java.util.*

@Parcelize
data class MovieGenre(
    val movieId: Long,
    val genreId: Long,
    val genreName: String,
    val created: Date
) : Parcelable {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as MovieGenre

        if (movieId != other.movieId) return false
        if (genreId != other.genreId) return false
        if (genreName != other.genreName) return false

        return true
    }

    override fun hashCode(): Int {
        var result = movieId.hashCode()
        result = 31 * result + genreId.hashCode()
        result = 31 * result + genreName.hashCode()
        return result
    }
}