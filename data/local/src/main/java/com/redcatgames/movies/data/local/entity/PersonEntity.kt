package com.redcatgames.movies.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity(tableName = "persons")
data class PersonEntity(
    @PrimaryKey val id: Long,
    val isAdult: Boolean,
    val alsoKnownAs: List<String>,
    val biography: String,
    val birthDay: Date?,
    val deathDay: Date?,
    val gender: Int,
    val homepage: String?,
    val imdbId: String?,
    val knownForDepartment: String,
    val name: String,
    val placeOfBirth: String?,
    val popularity: Double,
    val profilePath: String?,
    val created: Date
)
