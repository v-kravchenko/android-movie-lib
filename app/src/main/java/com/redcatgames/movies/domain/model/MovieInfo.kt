package com.redcatgames.movies.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class MovieInfo(
    val movie: Movie,
    val genres: List<MovieGenre>
) : Parcelable
