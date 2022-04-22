package com.redcatgames.musiclib.domain

class GetArtistListUseCase(private val artistRepository: ArtistRepository) {
    fun getArtistList() : List<Artist> {
        return artistRepository.getArtistList()
    }
}