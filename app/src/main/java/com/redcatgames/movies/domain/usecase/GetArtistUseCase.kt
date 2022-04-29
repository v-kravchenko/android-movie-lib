package com.redcatgames.movies.domain.usecase

import com.redcatgames.movies.domain.repository.ArtistRepository
import com.redcatgames.movies.domain.usecase.base.BaseUseCase
import javax.inject.Inject

class GetArtistUseCase @Inject constructor(private val artistRepository: ArtistRepository) :
    BaseUseCase() {
    operator fun invoke(id: Long) = artistRepository.getArtist(id)
}