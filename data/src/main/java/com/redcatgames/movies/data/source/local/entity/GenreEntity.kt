package com.redcatgames.movies.data.source.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity(tableName = "genres")
data class GenreEntity(@PrimaryKey val id: Long, val name: String, val created: Date)
