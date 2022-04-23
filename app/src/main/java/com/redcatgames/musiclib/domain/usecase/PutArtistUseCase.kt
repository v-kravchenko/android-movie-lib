package com.redcatgames.musiclib.domain.usecase

import com.redcatgames.musiclib.domain.model.Artist
import com.redcatgames.musiclib.domain.repository.ArtistRepository
import javax.inject.Inject

class PutArtistUseCase @Inject constructor(private val artistRepository: ArtistRepository) {
    suspend operator fun invoke(artist: Artist) = artistRepository.putArtist(artist)
}