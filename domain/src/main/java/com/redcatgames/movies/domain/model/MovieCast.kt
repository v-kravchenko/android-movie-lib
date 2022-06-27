package com.redcatgames.movies.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class MovieCast(
    val id: Long,
    val movieId: Long,
    val adult: Boolean,
    val gender: Int,
    val knownForDepartment: String?,
    val name: String,
    val originalName: String,
    val popularity: Double,
    val profilePath: String?,
    val castId: Long,
    val character: String,
    val creditId: String,
    val order: Int
) : Parcelable
