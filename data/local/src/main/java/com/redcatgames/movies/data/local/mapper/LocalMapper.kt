package com.redcatgames.movies.data.local.mapper

import com.redcatgames.movies.data.local.embedded.MovieInfoEntity
import com.redcatgames.movies.data.local.entity.*
import com.redcatgames.movies.domain.model.*
import com.redcatgames.movies.util.empty
import com.redcatgames.movies.util.now

fun DictionaryInfo.toEntity() = DictionaryEntity(language, created)

fun DictionaryEntity.toDictionaryInfo() = DictionaryInfo(language, created)

fun Country.toEntity() = CountryEntity(iso, englishName, nativeName, now())

fun CountryEntity.toCountry() = Country(iso, englishName, nativeName)

fun Language.toEntity() = LanguageEntity(iso, englishName, name, now())

fun LanguageEntity.toLanguage() = Language(iso, englishName, name)

fun PrimaryTranslation.toEntity() = PrimaryTranslationEntity(name, now())

fun PrimaryTranslationEntity.toPrimaryTranslation() = PrimaryTranslation(name)

fun Timezone.toEntity() = TimezoneEntity(iso, zoneName, now())

fun TimezoneEntity.toTimezone() = Timezone(iso, zoneName)

fun Genre.toEntity() = GenreEntity(id, name, now())

fun GenreEntity.toGenre() = Genre(id, name)

fun Movie.toEntity() =
    MovieEntity(
        id,
        isAdult,
        backdropPath ?: String.empty,
        genreIds,
        originalLanguage,
        originalTitle,
        overview,
        popularity,
        posterPath,
        releaseDate,
        title,
        video,
        voteAverage,
        voteCount,
        now()
    )

fun MovieEntity.toMovie() =
    Movie(
        id,
        isAdult,
        backdropPath,
        genreIds,
        originalLanguage,
        originalTitle,
        overview,
        popularity,
        posterPath,
        releaseDate,
        title,
        video,
        voteAverage,
        voteCount
    )

fun MovieGenre.toEntity() = MovieGenreEntity(movieId, genreId, genreName, now())

fun MovieGenreEntity.toMovieGenre() = MovieGenre(movieId, genreId, genreName)

fun MovieCast.toEntity() =
    MovieCastEntity(
        id,
        movieId,
        adult,
        gender,
        knownForDepartment ?: String.empty,
        name,
        originalName,
        popularity,
        profilePath ?: String.empty,
        castId,
        character,
        creditId,
        order,
        now()
    )

fun MovieCastEntity.toMovieCast() =
    MovieCast(
        id,
        movieId,
        adult,
        gender,
        knownForDepartment,
        name,
        originalName,
        popularity,
        profilePath,
        castId,
        character,
        creditId,
        order
    )

fun MovieCrew.toEntity() =
    MovieCrewEntity(
        id,
        movieId,
        adult,
        gender,
        knownForDepartment ?: String.empty,
        name,
        originalName,
        popularity,
        profilePath ?: String.empty,
        creditId,
        department,
        job,
        now()
    )

fun MovieCrewEntity.toMovieCrew() =
    MovieCrew(
        id,
        movieId,
        adult,
        gender,
        knownForDepartment,
        name,
        originalName,
        popularity,
        profilePath,
        creditId,
        department,
        job
    )

fun MovieInfoEntity.fromEntity() =
    MovieInfo(
        movie.toMovie(),
        genres.map { it.toMovieGenre() },
        casts.map { it.toMovieCast() },
        crews.map { it.toMovieCrew() }
    )

fun Person.toEntity() =
    PersonEntity(
        id,
        isAdult,
        alsoKnownAs,
        biography,
        birthDay,
        deathDay,
        gender,
        homepage,
        imdbId,
        knownForDepartment,
        name,
        placeOfBirth,
        popularity,
        profilePath,
        now()
    )
