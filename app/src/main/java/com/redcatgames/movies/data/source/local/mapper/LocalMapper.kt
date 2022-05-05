package com.redcatgames.movies.data.source.local.mapper

import com.redcatgames.movies.data.source.local.embedded.MovieInfoEntity
import com.redcatgames.movies.data.source.local.entity.*
import com.redcatgames.movies.domain.model.*
import com.redcatgames.movies.util.now

fun Country.mapTo() = CountryEntity(iso, englishName, nativeName, now())
fun CountryEntity.mapFrom() = Country(iso, englishName, nativeName)

fun Language.mapTo() = LanguageEntity(iso, englishName, name, now())
fun LanguageEntity.mapFrom() = Language(iso, englishName, name)

fun PrimaryTranslation.mapTo() = PrimaryTranslationEntity(name, now())
fun PrimaryTranslationEntity.mapFrom() = PrimaryTranslation(name)

fun Timezone.mapTo() = TimezoneEntity(iso, zoneName, now())
fun TimezoneEntity.mapFrom() = Timezone(iso, zoneName)

fun Genre.mapTo() = GenreEntity(id, name, now())
fun GenreEntity.mapFrom() = Genre(id, name)

fun Movie.mapTo() = MovieEntity(id, isAdult, backdropPath, genreIds, originalLanguage, originalTitle, overview, popularity, posterPath, releaseDate, title, video, voteAverage, voteCount, now())
fun MovieEntity.mapFrom() = Movie(id, isAdult, backdropPath, genreIds, originalLanguage, originalTitle, overview, popularity, posterPath, releaseDate, title, video, voteAverage, voteCount)

fun MovieGenre.mapTo() = MovieGenreEntity(movieId, genreId, genreName, now())
fun MovieGenreEntity.mapFrom() = MovieGenre(movieId, genreId, genreName)

fun MovieInfoEntity.mapFrom() = MovieInfo(movie.mapFrom(), genres.map { it.mapFrom() })