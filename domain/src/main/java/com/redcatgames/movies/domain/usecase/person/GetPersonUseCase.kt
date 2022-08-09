package com.redcatgames.movies.domain.usecase.person

import com.redcatgames.movies.domain.repository.MovieRepository
import javax.inject.Inject

class GetPersonUseCase @Inject constructor(private val movieRepository: MovieRepository) {
    operator fun invoke(personId: Long) = movieRepository.person(personId)
}
