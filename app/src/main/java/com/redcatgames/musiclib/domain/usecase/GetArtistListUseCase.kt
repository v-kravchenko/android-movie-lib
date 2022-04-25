package com.redcatgames.musiclib.domain.usecase

import com.redcatgames.musiclib.domain.repository.ArtistRepository
import com.redcatgames.musiclib.domain.usecase.base.BaseUseCase
import javax.inject.Inject

class GetArtistListUseCase @Inject constructor(private val artistRepository: ArtistRepository) :
    BaseUseCase() {
    operator fun invoke() = artistRepository.getArtistList()
}