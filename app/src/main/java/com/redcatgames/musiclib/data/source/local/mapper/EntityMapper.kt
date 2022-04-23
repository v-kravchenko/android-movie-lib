package com.redcatgames.musiclib.data.source.local.mapper

import com.redcatgames.musiclib.data.source.local.entity.ArtistEntity
import com.redcatgames.musiclib.domain.model.Artist

fun Artist.toEntity() = ArtistEntity(
    id = id,
    name = name,
    created = created
)

fun ArtistEntity.toRecord() = Artist(
    id = id,
    name = name,
    created = created
)
