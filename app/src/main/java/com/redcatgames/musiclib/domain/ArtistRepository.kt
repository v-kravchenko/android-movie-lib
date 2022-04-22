package com.redcatgames.musiclib.domain

interface ArtistRepository {
    fun getArtistList(): List<Artist>
    fun getArtist(id: Int): Artist
}