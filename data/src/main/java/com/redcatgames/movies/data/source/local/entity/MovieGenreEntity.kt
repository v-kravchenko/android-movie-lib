package com.redcatgames.movies.data.source.local.entity

import androidx.room.Entity
import java.util.*

@Entity(tableName = "movie_genres", primaryKeys = ["movieId", "genreId"])
data class MovieGenreEntity(
    val movieId: Long,
    val genreId: Long,
    val genreName: String,
    val created: Date
)
