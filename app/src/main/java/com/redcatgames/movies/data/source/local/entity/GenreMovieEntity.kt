package com.redcatgames.movies.data.source.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import java.util.*

@Entity(tableName = "countries")
data class GenreMovieEntity(
    @PrimaryKey
    val id : Long,
    val name : String,
    val created: Date
)
