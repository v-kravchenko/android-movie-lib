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
) : Parcelable
