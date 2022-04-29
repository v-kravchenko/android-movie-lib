package com.redcatgames.movies.data.source.local.mapper

import com.redcatgames.movies.data.source.local.entity.ArtistEntity
import com.redcatgames.movies.data.source.local.entity.MovieEntity
import com.redcatgames.movies.domain.model.Artist
import com.redcatgames.movies.domain.model.Movie

fun Artist.mapTo() = ArtistEntity(
    id = id,
    name = name,
    created = created
)

fun ArtistEntity.mapFrom() = Artist(
    id = id,
    name = name,
    created = created
)

fun Movie.mapTo() = MovieEntity(id, title, overview, created)
fun MovieEntity.mapFrom() = Movie(id, title, overview, created)
