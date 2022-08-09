package com.redcatgames.movies.domain.usecase.person

import com.redcatgames.movies.domain.model.PersonInfo
import com.redcatgames.movies.domain.repository.MovieRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetPersonInfoUseCase @Inject constructor(private val movieRepository: MovieRepository) {
    operator fun invoke(personId: Long): Flow<PersonInfo?> =
        movieRepository.personInfo(personId)
}
