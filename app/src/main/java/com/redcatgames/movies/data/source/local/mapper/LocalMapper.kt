package com.redcatgames.movies.data.source.local.mapper

import com.redcatgames.movies.data.source.local.entity.MovieEntity
import com.redcatgames.movies.domain.model.Movie

fun Movie.mapTo() = MovieEntity(id, isAdult, backdropPath, genreIds, originalLanguage, originalTitle, overview, popularity, posterPath, releaseDate, title, video, voteAverage, voteCount, created)
fun MovieEntity.mapFrom() = Movie(id, isAdult, backdropPath, genreIds, originalLanguage, originalTitle, overview, popularity, posterPath, releaseDate, title, video, voteAverage, voteCount, created)