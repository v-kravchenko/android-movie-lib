package com.redcatgames.movies.domain.usecase

import com.redcatgames.movies.domain.repository.ArtistRepository
import com.redcatgames.movies.domain.usecase.base.BaseUseCase
import javax.inject.Inject

class GetArtistByNameUseCase @Inject constructor(private val artistRepository: ArtistRepository) :
    BaseUseCase() {
    operator fun invoke(name: String) = artistRepository.getArtistByName(name)
}