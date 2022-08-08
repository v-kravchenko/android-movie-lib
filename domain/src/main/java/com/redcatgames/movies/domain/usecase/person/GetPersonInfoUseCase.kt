package com.redcatgames.movies.domain.usecase.person

import androidx.lifecycle.LiveData
import com.redcatgames.movies.domain.model.PersonInfo
import com.redcatgames.movies.domain.repository.MovieRepository
import javax.inject.Inject

class GetPersonInfoUseCase @Inject constructor(private val movieRepository: MovieRepository) {
    operator fun invoke(personId: Long): LiveData<PersonInfo?> =
        movieRepository.personInfo(personId)
}