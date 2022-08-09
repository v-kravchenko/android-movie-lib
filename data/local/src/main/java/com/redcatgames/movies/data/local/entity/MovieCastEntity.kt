package com.redcatgames.movies.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity(tableName = "movie_casts")
data class MovieCastEntity(
    @PrimaryKey val creditId: String,
    val personId: Long,
    val movieId: Long,
    val adult: Boolean,
    val gender: Int,
    val knownForDepartment: String,
    val name: String,
    val originalName: String,
    val popularity: Double,
    val profilePath: String,
    val castId: Long,
    val character: String,
    val order: Int,
    val created: Date
)
