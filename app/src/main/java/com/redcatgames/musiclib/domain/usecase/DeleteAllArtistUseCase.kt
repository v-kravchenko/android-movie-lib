package com.redcatgames.musiclib.domain.usecase

import com.redcatgames.musiclib.domain.repository.ArtistRepository
import com.redcatgames.musiclib.domain.usecase.base.BaseUseCase
import kotlinx.coroutines.withContext
import javax.inject.Inject

class DeleteAllArtistUseCase @Inject constructor(
    private val artistRepository: ArtistRepository
) : BaseUseCase() {
    suspend operator fun invoke() = withContext(io) {
        artistRepository.deleteAllArtist()
    }
}