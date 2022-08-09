package com.redcatgames.movies.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity(tableName = "person_casts")
data class PersonCastEntity(
    @PrimaryKey val creditId: String,
    val personId: Long,
    val isAdult: Boolean,
    val backdropPath: String?,
    val genreIds: List<Long>,
    val movieId: Long,
    val originalLanguage: String,
    val originalTitle: String,
    val overview: String,
    val popularity: Double,
    val posterPath: String?,
    val releaseDate: Date?,
    val title: String,
    val isVideo: Boolean,
    val voteAverage: Double,
    val voteCount: Int,
    val character: String?,
    val order: Int,
    val created: Date
)
