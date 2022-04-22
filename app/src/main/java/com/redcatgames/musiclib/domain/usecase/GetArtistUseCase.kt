package com.redcatgames.musiclib.domain.usecase

import com.redcatgames.musiclib.domain.repository.ArtistRepository
import javax.inject.Inject

class GetArtistUseCase @Inject constructor(private val artistRepository: ArtistRepository) {
    operator fun invoke(id: Int) = artistRepository.getArtist(id)
}