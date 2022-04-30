package com.redcatgames.movies.presentation.movie

import android.content.Context
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.redcatgames.movies.domain.usecase.movie.GetMovieUseCase
import com.redcatgames.movies.domain.usecase.movie.GetPopularMoviesUseCase
import com.redcatgames.movies.domain.usecase.movie.LoadMovieUseCase
import com.redcatgames.movies.domain.usecase.movie.LoadPopularMoviesUseCase
import com.redcatgames.movies.domain.util.UseCaseResult
import com.redcatgames.movies.presentation.base.BaseViewModel
import com.redcatgames.movies.presentation.util.SingleLiveEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class MovieViewModel @Inject constructor(
    @ApplicationContext appContext: Context,
    private val savedStateHandle: SavedStateHandle,
    getMovieUserCase: GetMovieUseCase,
    private val loadMovieUseCase: LoadMovieUseCase
) : BaseViewModel(appContext) {

    private val moveId: Long
        get() = savedStateHandle.get<Long>("movieId")
            ?: throw RuntimeException("Parameter moveId is null!")

    val movie = getMovieUserCase(moveId)
    val loadMovieEvent = SingleLiveEvent<UseCaseResult<Unit>>()

    init {
        viewModelScope.launch {
            loadMovieUseCase(moveId).run {
                loadMovieEvent.postValue(this)
            }
        }
    }
}