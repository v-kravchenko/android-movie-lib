package com.redcatgames.movies.presentation.popular

import android.content.Context
import androidx.lifecycle.viewModelScope
import com.redcatgames.movies.domain.model.Movie
import com.redcatgames.movies.domain.usecase.movie.GetPopularMoviesUseCase
import com.redcatgames.movies.domain.usecase.movie.LoadPopularMoviesUseCase
import com.redcatgmes.movies.baseui.BaseViewModel
import com.redcatgmes.movies.baseui.util.SingleLiveEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import kotlinx.coroutines.launch

@HiltViewModel
class PopularViewModel
@Inject
constructor(
    @ApplicationContext appContext: Context,
    getPopularMoviesUseCase: GetPopularMoviesUseCase,
    private val loadPopularMoviesUseCase: LoadPopularMoviesUseCase
) : BaseViewModel(appContext) {

    val popularMovies = getPopularMoviesUseCase()
    val loadPopularMoviesEvent = SingleLiveEvent<Result<List<Movie>>>()

    init {
        loadNextPage()
    }

    fun loadNextPage() {
        viewModelScope.launch {
            loadPopularMoviesUseCase((0..10).random()).run {
                loadPopularMoviesEvent.postValue(this)
            }
        }
    }
}
