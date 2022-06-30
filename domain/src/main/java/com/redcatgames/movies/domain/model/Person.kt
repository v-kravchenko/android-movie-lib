package com.redcatgames.movies.domain.model

data class Person(
    val id: Long,
    val isAdult: Boolean,
    val alsoKnownAs: List<String>,
    val biography: String,
    val birthDay: String?,
    val deathDay: String?,
    val gender: Int,
    val homepage: String?,
    val imdbId: String?,
    val knownForDepartment: String,
    val name: String,
    val placeOfBirth: String?,
    val popularity: Double,
    val profilePath: String?
)
