package com.redcatgames.movies.data.source.remote.mapper

import com.redcatgames.movies.data.source.remote.response.configuration.ConfigurationCountriesResult
import com.redcatgames.movies.data.source.remote.response.configuration.ConfigurationResult
import com.redcatgames.movies.data.source.remote.response.discover.movie.DiscoverMovieResult
import com.redcatgames.movies.data.source.remote.response.movie.MovieResult
import com.redcatgames.movies.domain.model.Country
import com.redcatgames.movies.domain.model.ImageConfig
import com.redcatgames.movies.domain.model.Movie
import com.redcatgames.movies.util.now

fun ConfigurationCountriesResult.mapFrom() = Country(
    iso, englishName, nativeName, now()
)

fun ConfigurationResult.Images.mapFrom() = ImageConfig(
    baseUrl = baseUrl,
    secureBaseUrl = secureBaseUrl,
    backdropSizes = backdropSizes,
    logoSizes = logoSizes,
    posterSizes = posterSizes,
    profileSizes = profileSizes,
    stillSizes = stillSizes,
    created = now()
)

fun DiscoverMovieResult.Movie.mapFrom() = Movie(
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
    voteCount = voteCount,
    created = now()
)

fun MovieResult.mapFrom() = Movie(
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
    voteCount = voteCount,
    created = now()
)