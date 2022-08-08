package com.redcatgames.movies.domain.usecase.person

import com.redcatgames.movies.domain.repository.MovieRepository
import javax.inject.Inject

class LoadPersonUseCase @Inject constructor(private val movieRepository: MovieRepository) {
    suspend operator fun invoke(personId: Long) = movieRepository.loadPersonInfo(personId)
}
