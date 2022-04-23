package com.redcatgames.musiclib.domain.repository

import com.redcatgames.musiclib.domain.model.Artist

interface ArtistRepository {
    fun getArtistList(): List<Artist>
    fun getArtist(id: Long): Artist
}