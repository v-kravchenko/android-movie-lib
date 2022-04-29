package com.redcatgames.movies.data.source.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import java.util.*

@Entity(tableName = "movies")
data class MovieEntity(
    @PrimaryKey(autoGenerate = true)
    val id : Long,

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
)
