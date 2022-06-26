package com.redcatgames.movies.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class MovieInfo(
    val movie: com.redcatgames.movies.domain.model.Movie,
    val genres: List<com.redcatgames.movies.domain.model.MovieGenre>,
    val casts: List<com.redcatgames.movies.domain.model.MovieCast>,
    val crews: List<com.redcatgames.movies.domain.model.MovieCrew>
) : Parcelable
