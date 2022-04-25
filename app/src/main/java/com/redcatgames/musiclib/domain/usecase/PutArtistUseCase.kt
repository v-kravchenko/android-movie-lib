package com.redcatgames.musiclib.domain.usecase

import com.redcatgames.musiclib.domain.model.Artist
import com.redcatgames.musiclib.domain.repository.ArtistRepository
import com.redcatgames.musiclib.domain.usecase.base.BaseUseCase
import kotlinx.coroutines.withContext
import javax.inject.Inject

class PutArtistUseCase @Inject constructor(private val artistRepository: ArtistRepository) :
    BaseUseCase() {
    suspend operator fun invoke(artist: Artist) = withContext(io) {
        artistRepository.putArtist(artist)
    }
}