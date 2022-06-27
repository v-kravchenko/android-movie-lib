package com.redcatgames.movies.presentation.popular

import android.content.Context
import androidx.lifecycle.viewModelScope
import com.redcatgames.movies.domain.model.Movie
import com.redcatgames.movies.domain.usecase.movie.GetPopularMoviesUseCase
import com.redcatgames.movies.domain.usecase.movie.LoadPopularMoviesUseCase
import com.redcatgames.movies.presentation.base.BaseViewModel
import com.redcatgames.movies.presentation.util.SingleLiveEvent
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

  private var page = 0
  val popularMovies = getPopularMoviesUseCase()
  val loadPopularMoviesEvent = SingleLiveEvent<Result<List<Movie>>>()

  init {
    loadNextPage()
  }

  fun loadNextPage() {
    viewModelScope.launch {
      loadPopularMoviesUseCase((0..10).random()).run { loadPopularMoviesEvent.postValue(this) }
    }
  }
}
