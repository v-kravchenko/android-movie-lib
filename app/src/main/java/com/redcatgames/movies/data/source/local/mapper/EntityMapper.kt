package com.redcatgames.movies.data.source.local.mapper

import com.redcatgames.movies.data.source.local.entity.ArtistEntity
import com.redcatgames.movies.domain.model.Artist

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
