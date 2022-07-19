package com.redcatgames.movies.presentation.home

import android.content.Context
import androidx.lifecycle.viewModelScope
import com.redcatgames.movies.domain.model.Movie
import com.redcatgames.movies.domain.usecase.movie.GetPopularMoviesUseCase
import com.redcatgames.movies.domain.usecase.movie.LoadPopularMoviesUseCase
import com.redcatgmes.movies.baseui.BaseViewModel
import com.redcatgmes.movies.baseui.util.SingleLiveEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    @ApplicationContext appContext: Context,
    getPopularMoviesUseCase: GetPopularMoviesUseCase,
    private val loadPopularMoviesUseCase: LoadPopularMoviesUseCase
) : BaseViewModel(appContext) {
    val popularMovies = getPopularMoviesUseCase()
    val loadPopularMoviesEvent = SingleLiveEvent<Result<List<Movie>>>()

    init {
        viewModelScope.launch {
            loadPopularMoviesUseCase().run { loadPopularMoviesEvent.postValue(this) }
        }
    }
}
