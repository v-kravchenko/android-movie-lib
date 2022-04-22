package com.redcatgames.musiclib.domain

class GetArtistUserCase(private val artistRepository: ArtistRepository) {
    operator fun invoke(id: Int) = artistRepository.getArtist(id)
}