package com.redcatgames.musiclib.data.source.local.mapper

import com.redcatgames.musiclib.data.source.local.entity.ArtistEntity
import com.redcatgames.musiclib.domain.model.Artist

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
