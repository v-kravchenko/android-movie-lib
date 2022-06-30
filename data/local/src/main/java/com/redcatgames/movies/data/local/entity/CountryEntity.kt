package com.redcatgames.movies.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity(tableName = "countries")
data class CountryEntity(
    @PrimaryKey val iso: String,
    val englishName: String,
    val nativeName: String,
    val created: Date
)
