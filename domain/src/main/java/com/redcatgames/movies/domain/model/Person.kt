package com.redcatgames.movies.domain.model

import java.util.*

data class Person(
    val id: Long,
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
    val profilePath: String?
)
