package com.redcatgames.movies.presentation.movie

import android.content.Context
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.redcatgames.movies.domain.usecase.movie.GetMovieInfoUseCase
import com.redcatgames.movies.domain.usecase.movie.GetMovieUseCase
import com.redcatgames.movies.domain.usecase.movie.LoadMovieInfoUseCase
import com.redcatgames.movies.domain.util.UseCaseResult
import com.redcatgames.movies.presentation.base.BaseViewModel
import com.redcatgames.movies.presentation.util.SingleLiveEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieViewModel @Inject constructor(
    @ApplicationContext appContext: Context,
    savedStateHandle: SavedStateHandle,
    getMovieInfoUseCase: GetMovieInfoUseCase,
    private val loadMovieInfoUseCase: LoadMovieInfoUseCase
) : BaseViewModel(appContext) {

    private val args = MovieFragmentArgs.fromSavedStateHandle(savedStateHandle)
    val movieInfo = getMovieInfoUseCase(args.movieId)
    val loadMovieEvent = SingleLiveEvent<UseCaseResult<Unit, String?>>()

    init {
        loadMovieInfo()
    }

    private fun loadMovieInfo() = viewModelScope.launch {
        loadMovieInfoUseCase(args.movieId).run {
            loadMovieEvent.postValue(this)
        }
    }
}