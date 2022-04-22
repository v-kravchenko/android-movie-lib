package com.redcatgames.musiclib.domain

class GetArtistListUseCase(private val artistRepository: ArtistRepository) {
    operator fun invoke() = artistRepository.getArtistList()
}