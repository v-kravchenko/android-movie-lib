package com.redcatgames.movies.domain.model

data class MovieCrew(
    val personId: Long,
    val movieId: Long,
    val adult: Boolean,
    val gender: Int,
    val knownForDepartment: String?,
    val name: String,
    val originalName: String,
    val popularity: Double,
    val profilePath: String?,
    val creditId: String,
    val department: String,
    val job: String
)
