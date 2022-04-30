package com.redcatgames.movies.presentation.home

import android.content.Context
import androidx.lifecycle.viewModelScope
import com.redcatgames.movies.domain.model.Artist
import com.redcatgames.movies.domain.usecase.*
import com.redcatgames.movies.domain.usecase.movie.GetPopularMovieListUseCase
import com.redcatgames.movies.domain.usecase.movie.LoadPopularMovieListUseCase
import com.redcatgames.movies.domain.util.UseCaseResult
import com.redcatgames.movies.presentation.base.BaseViewModel
import com.redcatgames.movies.presentation.util.SingleLiveEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    @ApplicationContext appContext: Context,
    getPopularMovieListUseCase: GetPopularMovieListUseCase,
    private val loadPopularMovieListUseCase: LoadPopularMovieListUseCase
) : BaseViewModel(appContext) {

    val popularMovieList = getPopularMovieListUseCase()
    val loadPopularMovieListEvent = SingleLiveEvent<UseCaseResult<Int>>()

    init {
        viewModelScope.launch {
            loadPopularMovieListUseCase().run {
                loadPopularMovieListEvent.postValue(this)
            }
        }
    }
}