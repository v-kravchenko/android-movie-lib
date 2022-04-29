package com.redcatgames.movies.domain.usecase

import com.redcatgames.movies.domain.repository.ArtistRepository
import com.redcatgames.movies.domain.usecase.base.BaseUseCase
import kotlinx.coroutines.withContext
import javax.inject.Inject

class DeleteAllArtistUseCase @Inject constructor(
    private val artistRepository: ArtistRepository
) : BaseUseCase() {
    suspend operator fun invoke() = withContext(io) {
        artistRepository.deleteAllArtist()
    }
}