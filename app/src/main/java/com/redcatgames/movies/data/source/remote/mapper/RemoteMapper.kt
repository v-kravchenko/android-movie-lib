package com.redcatgames.movies.data.source.remote.mapper

import com.redcatgames.movies.data.source.remote.json.discover.movie.Result
import com.redcatgames.movies.domain.model.Movie
import com.redcatgames.movies.util.now

fun Result.Movie.mapFrom() = Movie(
    id = id,
    isAdult = isAdult,
    backdropPath = backdropPath,
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