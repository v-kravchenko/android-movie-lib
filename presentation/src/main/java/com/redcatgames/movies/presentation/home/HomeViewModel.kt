package com.redcatgames.movies.presentation.home

import android.content.Context
import androidx.lifecycle.viewModelScope
import com.redcatgames.movies.domain.model.Movie
import com.redcatgames.movies.domain.usecase.movie.GetMostVotesMoviesUseCase
import com.redcatgames.movies.domain.usecase.movie.GetPopularMoviesUseCase
import com.redcatgames.movies.domain.usecase.movie.LoadMostVotesMoviesUseCase
import com.redcatgames.movies.domain.usecase.movie.LoadPopularMoviesUseCase
import com.redcatgmes.movies.baseui.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    @ApplicationContext appContext: Context,
    getPopularMoviesUseCase: GetPopularMoviesUseCase,
    getMostVotesMoviesUseCase: GetMostVotesMoviesUseCase,
    private val loadPopularMoviesUseCase: LoadPopularMoviesUseCase,
    private val loadMostVotesMoviesUseCase: LoadMostVotesMoviesUseCase
) : BaseViewModel(appContext) {

    sealed class Event {
        data class PopularMoviesLoaded(val result: Result<List<Movie>>) : Event()
        data class MostVotesMoviesLoaded(val result: Result<List<Movie>>) : Event()
    }

    val popularMovies = getPopularMoviesUseCase()
    val mostVotesMovies = getMostVotesMoviesUseCase()

    private val eventChannel = Channel<Event>(Channel.BUFFERED)
    val events = eventChannel.receiveAsFlow()

    init {
        viewModelScope.launch {
            loadPopularMoviesUseCase().run {
                eventChannel.send(Event.PopularMoviesLoaded(this))
            }
            loadMostVotesMoviesUseCase().run {
                eventChannel.send(Event.MostVotesMoviesLoaded(this))
            }
        }
    }
}
