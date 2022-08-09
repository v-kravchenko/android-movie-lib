package com.redcatgames.movies.data.local.mapper

import com.redcatgames.movies.data.local.embedded.MovieInfoEntity
import com.redcatgames.movies.data.local.embedded.PersonInfoEntity
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
        now())

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
        voteCount)

fun MovieGenre.toEntity() = MovieGenreEntity(movieId, genreId, genreName, now())

fun MovieGenreEntity.toMovieGenre() = MovieGenre(movieId, genreId, genreName)

fun PersonCast.toEntity() =
    PersonCastEntity(
        creditId,
        personId,
        isAdult,
        backdropPath,
        genreIds,
        movieId,
        originalLanguage,
        originalTitle,
        overview,
        popularity,
        posterPath,
        releaseDate,
        title,
        isVideo,
        voteAverage,
        voteCount,
        character,
        order,
        now())

fun MovieCast.toEntity() =
    MovieCastEntity(
        creditId,
        personId,
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
        order,
        now())

fun PersonCastEntity.toPersonCast() =
    PersonCast(
        creditId,
        personId,
        isAdult,
        backdropPath,
        genreIds,
        movieId,
        originalLanguage,
        originalTitle,
        overview,
        popularity,
        posterPath,
        releaseDate,
        title,
        isVideo,
        voteAverage,
        voteCount,
        character,
        order)

fun MovieCastEntity.toMovieCast() =
    MovieCast(
        personId,
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
        order)

fun PersonCrew.toEntity() =
    PersonCrewEntity(
        creditId,
        personId,
        isAdult,
        backdropPath,
        genreIds,
        movieId,
        originalLanguage,
        originalTitle,
        overview,
        popularity,
        posterPath,
        releaseDate,
        title,
        isVideo,
        voteAverage,
        voteCount,
        department,
        job,
        now())

fun MovieCrew.toEntity() =
    MovieCrewEntity(
        creditId,
        personId,
        movieId,
        adult,
        gender,
        knownForDepartment ?: String.empty,
        name,
        originalName,
        popularity,
        profilePath ?: String.empty,
        department,
        job,
        now())

fun PersonCrewEntity.toPersonCrew() =
    PersonCrew(
        creditId,
        personId,
        isAdult,
        backdropPath,
        genreIds,
        movieId,
        originalLanguage,
        originalTitle,
        overview,
        popularity,
        posterPath,
        releaseDate,
        title,
        isVideo,
        voteAverage,
        voteCount,
        department,
        job)

fun MovieCrewEntity.toMovieCrew() =
    MovieCrew(
        personId,
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
        job)

fun MovieInfoEntity.fromEntity() =
    MovieInfo(
        movie.toMovie(),
        genres.map { it.toMovieGenre() },
        casts.map { it.toMovieCast() },
        crews.map { it.toMovieCrew() })

fun PersonInfoEntity.fromEntity() =
    PersonInfo(person.toPerson(), casts.map { it.toPersonCast() }, crews.map { it.toPersonCrew() })

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
        now())

fun PersonEntity.toPerson() =
    Person(
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
        profilePath)
