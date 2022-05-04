package com.redcatgames.movies.data.source.local.mapper

import com.redcatgames.movies.data.source.local.embedded.MovieInfoEntity
import com.redcatgames.movies.data.source.local.entity.*
import com.redcatgames.movies.domain.model.*

fun Country.mapTo() = CountryEntity(iso, englishName, nativeName, created)
fun CountryEntity.mapFrom() = Country(iso, englishName, nativeName, created)

fun Language.mapTo() = LanguageEntity(iso, englishName, name, created)
fun LanguageEntity.mapFrom() = Language(iso, englishName, name, created)

fun PrimaryTranslation.mapTo() = PrimaryTranslationEntity(name, created)
fun PrimaryTranslationEntity.mapFrom() = PrimaryTranslation(name, created)

fun Timezone.mapTo() = TimezoneEntity(iso, zoneName, created)
fun TimezoneEntity.mapFrom() = Timezone(iso, zoneName, created)

fun Genre.mapTo() = GenreEntity(id, name, created)
fun GenreEntity.mapFrom() = Genre(id, name, created)

fun Movie.mapTo() = MovieEntity(id, isAdult, backdropPath, genreIds, originalLanguage, originalTitle, overview, popularity, posterPath, releaseDate, title, video, voteAverage, voteCount, created)
fun MovieEntity.mapFrom() = Movie(id, isAdult, backdropPath, genreIds, originalLanguage, originalTitle, overview, popularity, posterPath, releaseDate, title, video, voteAverage, voteCount, created)

fun MovieGenre.mapTo() = MovieGenreEntity(movieId, genreId, genreName, created)
fun MovieGenreEntity.mapFrom() = MovieGenre(movieId, genreId, genreName, created)

fun MovieInfoEntity.mapFrom() = MovieInfo(movie.mapFrom(), genres.map { it.mapFrom() })