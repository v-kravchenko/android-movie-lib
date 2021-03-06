package com.redcatgames.movies.presentation.movie

import android.content.Context
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.redcatgames.movies.domain.usecase.movie.GetMovieInfoUseCase
import com.redcatgames.movies.domain.usecase.movie.LoadMovieInfoUseCase
import com.redcatgmes.movies.baseui.BaseViewModel
import com.redcatgmes.movies.baseui.util.SingleLiveEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import kotlinx.coroutines.launch

@HiltViewModel
class MovieViewModel
@Inject
constructor(
    @ApplicationContext appContext: Context,
    savedStateHandle: SavedStateHandle,
    getMovieInfoUseCase: GetMovieInfoUseCase,
    private val loadMovieInfoUseCase: LoadMovieInfoUseCase
) : BaseViewModel(appContext) {

    private val args = MovieFragmentArgs.fromSavedStateHandle(savedStateHandle)
    val movieInfo = getMovieInfoUseCase(args.movieId)
    val loadMovieEvent = SingleLiveEvent<Result<Unit>>()

    init {
        loadMovieInfo()
    }

    private fun loadMovieInfo() =
        viewModelScope.launch {
            loadMovieInfoUseCase(args.movieId).run { loadMovieEvent.postValue(this) }
        }
}
