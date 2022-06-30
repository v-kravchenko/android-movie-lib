package com.redcatgames.movies.domain.model

data class MovieInfo(
    val movie: Movie,
    val genres: List<MovieGenre>,
    val casts: List<MovieCast>,
    val crews: List<MovieCrew>
)
