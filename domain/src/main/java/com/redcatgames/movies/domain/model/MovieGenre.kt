package com.redcatgames.movies.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class MovieGenre(val movieId: Long, val genreId: Long, val genreName: String) : Parcelable
