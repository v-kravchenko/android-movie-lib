package com.redcatgames.musiclib.domain

class GetArtistUserCase(private val artistRepository: ArtistRepository) {
    fun getArtist(id: Int) : Artist {
        return artistRepository.getArtist(id)
    }
}