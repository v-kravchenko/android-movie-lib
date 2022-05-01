package com.redcatgames.movies.data.source.local.mapper

import com.redcatgames.movies.data.source.local.entity.ImageConfigEntity
import com.redcatgames.movies.data.source.local.entity.MovieEntity
import com.redcatgames.movies.domain.model.ImageConfig
import com.redcatgames.movies.domain.model.Movie

fun ImageConfig.mapTo() = ImageConfigEntity(id, baseUrl, secureBaseUrl, backdropSizes, logoSizes, posterSizes, profileSizes, stillSizes, created)
fun ImageConfigEntity.mapFrom() = ImageConfig(id, baseUrl, secureBaseUrl, backdropSizes, logoSizes, posterSizes, profileSizes, stillSizes, created)

fun Movie.mapTo() = MovieEntity(id, isAdult, backdropPath, originalLanguage, originalTitle, overview, popularity, posterPath, releaseDate, title, video, voteAverage, voteCount, created)
fun MovieEntity.mapFrom() = Movie(id, isAdult, backdropPath, originalLanguage, originalTitle, overview, popularity, posterPath, releaseDate, title, video, voteAverage, voteCount, created)