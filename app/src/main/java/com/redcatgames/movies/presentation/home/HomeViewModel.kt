package com.redcatgames.movies.presentation.home

import android.content.Context
import androidx.lifecycle.viewModelScope
import com.redcatgames.movies.domain.model.Artist
import com.redcatgames.movies.domain.usecase.*
import com.redcatgames.movies.domain.usecase.movie.GetPopularMovieListUseCase
import com.redcatgames.movies.domain.usecase.movie.LoadPopularMovieListUseCase
import com.redcatgames.movies.presentation.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    @ApplicationContext appContext: Context,
    private val getPopularMovieListUseCase: GetPopularMovieListUseCase,
    private val loadPopularMovieListUseCase: LoadPopularMovieListUseCase
) : BaseViewModel(appContext) {

    val popularMovieList = getPopularMovieListUseCase()

    init {
        viewModelScope.launch {
            val result = loadPopularMovieListUseCase()
            result.onSuccess {
                Timber.d("loadPopularMovieListUseCase onSuccess")
            }
            result.onFailure {
                Timber.d("loadPopularMovieListUseCase onFailure: $it")
            }
        }
    }
}