package com.redcatgames.musiclib.domain.usecase

import com.redcatgames.musiclib.domain.repository.ArtistRepository
import javax.inject.Inject

class DeleteAllArtistUseCase @Inject constructor(private val artistRepository: ArtistRepository) {
    suspend operator fun invoke() = artistRepository.deleteAllArtist()
}