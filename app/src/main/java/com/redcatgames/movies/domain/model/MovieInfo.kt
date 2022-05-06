package com.redcatgames.movies.domain.model

import android.os.Parcelable
import com.redcatgames.movies.data.source.local.entity.MovieCastEntity
import com.redcatgames.movies.data.source.local.entity.MovieCrewEntity
import kotlinx.parcelize.Parcelize

@Parcelize
data class MovieInfo(
    val movie: Movie,
    val genres: List<MovieGenre>,
    val casts: List<MovieCast>,
    val crews: List<MovieCrew>
) : Parcelable
