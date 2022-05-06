package com.redcatgames.movies.data.source.local.mapper

import com.redcatgames.movies.data.source.local.embedded.MovieInfoEntity
import com.redcatgames.movies.data.source.local.entity.*
import com.redcatgames.movies.domain.model.*
import com.redcatgames.movies.util.now

fun Country.toEntity() = CountryEntity(iso, englishName, nativeName, now())
fun CountryEntity.fromEntity() = Country(iso, englishName, nativeName)

fun Language.toEntity() = LanguageEntity(iso, englishName, name, now())
fun LanguageEntity.fromEntity() = Language(iso, englishName, name)

fun PrimaryTranslation.toEntity() = PrimaryTranslationEntity(name, now())
fun PrimaryTranslationEntity.fromEntity() = PrimaryTranslation(name)

fun Timezone.toEntity() = TimezoneEntity(iso, zoneName, now())
fun TimezoneEntity.fromEntity() = Timezone(iso, zoneName)

fun Genre.toEntity() = GenreEntity(id, name, now())
fun GenreEntity.fromEntity() = Genre(id, name)

fun Movie.toEntity() = MovieEntity(id, isAdult, backdropPath, genreIds, originalLanguage, originalTitle, overview, popularity, posterPath, releaseDate, title, video, voteAverage, voteCount, now())
fun MovieEntity.fromEntity() = Movie(id, isAdult, backdropPath, genreIds, originalLanguage, originalTitle, overview, popularity, posterPath, releaseDate, title, video, voteAverage, voteCount)

fun MovieGenre.toEntity() = MovieGenreEntity(movieId, genreId, genreName, now())
fun MovieGenreEntity.fromEntity() = MovieGenre(movieId, genreId, genreName)

fun MovieCast.toEntity() = MovieCastEntity(id, movieId, adult, gender, knownForDepartment, name, originalName, popularity, profilePath, castId, character, creditId, order, now())
fun MovieCastEntity.fromEntity() = MovieCast(id, movieId, adult, gender, knownForDepartment, name, originalName, popularity, profilePath, castId, character, creditId, order)

fun MovieCrew.toEntity() = MovieCrewEntity(id, movieId, adult, gender, knownForDepartment, name, originalName, popularity, profilePath, creditId, department, job, now())
fun MovieCrewEntity.fromEntity() = MovieCrew(id, movieId, adult, gender, knownForDepartment, name, originalName, popularity, profilePath, creditId, department, job)

fun MovieInfoEntity.fromEntity() = MovieInfo(
    movie.fromEntity(),
    genres.map { it.fromEntity() },
    casts.map { it.fromEntity() },
    crews.map { it.fromEntity() }
)