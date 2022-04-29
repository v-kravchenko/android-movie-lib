package com.redcatgames.movies.data.source.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity(tableName = "movies")
data class MovieEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long,
    val title: String,
    val overview: String,
    val created: Date
)
