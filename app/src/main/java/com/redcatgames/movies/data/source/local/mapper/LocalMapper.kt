package com.redcatgames.movies.data.source.local.mapper

import com.redcatgames.movies.data.source.local.entity.CountryEntity
import com.redcatgames.movies.data.source.local.entity.MovieEntity
import com.redcatgames.movies.domain.model.Country
import com.redcatgames.movies.domain.model.Movie

fun Country.mapTo() = CountryEntity(iso, englishName, nativeName, created)
fun CountryEntity.mapFrom() = Country(iso, englishName, nativeName, created)

fun Movie.mapTo() = MovieEntity(id, isAdult, backdropPath, genreIds, originalLanguage, originalTitle, overview, popularity, posterPath, releaseDate, title, video, voteAverage, voteCount, created)
fun MovieEntity.mapFrom() = Movie(id, isAdult, backdropPath, genreIds, originalLanguage, originalTitle, overview, popularity, posterPath, releaseDate, title, video, voteAverage, voteCount, created)
