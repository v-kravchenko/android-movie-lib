package com.redcatgames.movies.data.source.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity(tableName = "languages")
data class LanguageEntity(
    @PrimaryKey val iso: String,
    val englishName: String,
    val name: String,
    val created: Date
)
