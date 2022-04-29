package com.redcatgames.movies.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import java.util.*

@Parcelize
data class Movie(
    val id: Long,
    val isAdult : Boolean,
    val backdropPath : String,
    //TODO val genreIds : List<Int>,
    val originalLanguage : String,
    val originalTitle : String,
    val overview : String,
    val popularity : Double,
    val posterPath : String,
    val releaseDate : String,
    val title : String,
    val video : Boolean,
    val voteAverage : Double,
    val voteCount : Int,
    val created: Date
) : Parcelable
