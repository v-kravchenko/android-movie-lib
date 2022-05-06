package com.redcatgames.movies.data.source.remote.mapper

import com.redcatgames.movies.data.source.remote.response.configuration.ConfigurationCountriesResult
import com.redcatgames.movies.data.source.remote.response.configuration.ConfigurationLanguagesResult
import com.redcatgames.movies.data.source.remote.response.configuration.ConfigurationResult
import com.redcatgames.movies.data.source.remote.response.configuration.ConfigurationTimezonesResult
import com.redcatgames.movies.data.source.remote.response.discover.movie.DiscoverMovieResult
import com.redcatgames.movies.data.source.remote.response.movie.GenreResult
import com.redcatgames.movies.data.source.remote.response.movie.MovieResult
import com.redcatgames.movies.data.source.remote.response.movie.credits.MovieCreditsResult
import com.redcatgames.movies.domain.model.*
import com.redcatgames.movies.util.empty

fun ConfigurationCountriesResult.toCountry() = Country(
    iso, englishName, nativeName
)

fun ConfigurationLanguagesResult.toLanguage() = Language(
    iso, englishName, name
)

fun ConfigurationTimezonesResult.toTimezoneList(): List<Timezone> {
    return mutableListOf<Timezone>().apply {
        addAll(zones.map { Timezone(iso, it) })
    }.toList()
}

fun GenreResult.Genre.toGenre() = Genre(
    id, name ?: String.empty
)

fun ConfigurationResult.Images.toImageConfig() = ImageConfig(
    baseUrl = baseUrl,
    secureBaseUrl = secureBaseUrl,
    backdropSizes = backdropSizes,
    logoSizes = logoSizes,
    posterSizes = posterSizes,
    profileSizes = profileSizes,
    stillSizes = stillSizes
)

fun DiscoverMovieResult.Movie.toMovie() = Movie(
    id = id,
    isAdult = isAdult,
    backdropPath = backdropPath,
    genreIds = genreIds,
    originalLanguage = originalLanguage,
    originalTitle = originalTitle,
    overview = overview,
    popularity = popularity,
    posterPath = posterPath,
    releaseDate = releaseDate,
    title = title,
    video = video,
    voteAverage = voteAverage,
    voteCount = voteCount
)

fun MovieResult.toMovie() = Movie(
    id = id,
    isAdult = isAdult,
    backdropPath = backdropPath,
    genreIds = genres.map { it.id },
    originalLanguage = originalLanguage,
    originalTitle = originalTitle,
    overview = overview,
    popularity = popularity,
    posterPath = posterPath,
    releaseDate = releaseDate,
    title = title,
    video = video,
    voteAverage = voteAverage,
    voteCount = voteCount
)

fun MovieResult.Genre.toMovieGenre(movie: Movie) = MovieGenre(
    movieId = movie.id,
    genreId = id,
    genreName = name
)

fun MovieCreditsResult.toMovieCastList(): List<MovieCast> {
    return mutableListOf<MovieCast>().apply {
        addAll(castList.map {
            MovieCast(
                id = it.id,
                movieId = this@toMovieCastList.movieId,
                adult = it.adult,
                gender = it.gender,
                knownForDepartment = it.knownForDepartment,
                name = it.name,
                originalName = it.originalName,
                popularity = it.popularity,
                profilePath = it.profilePath,
                castId = it.castId,
                character = it.character,
                creditId = it.creditId,
                order = it.order
            )
        })
    }.toList()
}

fun MovieCreditsResult.toMovieCrewList(): List<MovieCrew> {
    return mutableListOf<MovieCrew>().apply {
        addAll(crewList.map {
            MovieCrew(
                id = it.id,
                movieId = this@toMovieCrewList.movieId,
                adult = it.adult,
                gender = it.gender,
                knownForDepartment = it.knownForDepartment,
                name = it.name,
                originalName = it.originalName,
                popularity = it.popularity,
                profilePath = it.profilePath,
                creditId = it.creditId,
                department = it.department,
                job = it.job
            )
        })
    }.toList()
}