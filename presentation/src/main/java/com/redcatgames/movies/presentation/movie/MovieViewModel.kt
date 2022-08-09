package com.redcatgames.movies.presentation.movie

import android.content.Context
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.redcatgames.movies.domain.usecase.movie.GetMovieInfoUseCase
import com.redcatgames.movies.domain.usecase.movie.LoadMovieInfoUseCase
import com.redcatgmes.movies.baseui.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import timber.log.Timber

@HiltViewModel
class MovieViewModel
@Inject
constructor(
    @ApplicationContext appContext: Context,
    savedStateHandle: SavedStateHandle,
    getMovieInfoUseCase: GetMovieInfoUseCase,
    private val loadMovieInfoUseCase: LoadMovieInfoUseCase
) : BaseViewModel(appContext) {

    sealed class Event {
        data class MovieInfoLoaded(val result: Result<Unit>) : Event()
    }

    private val args = MovieFragmentArgs.fromSavedStateHandle(savedStateHandle)
    val movieInfo = getMovieInfoUseCase(args.movieId)

    private val eventChannel = Channel<Event>(Channel.BUFFERED)
    val events = eventChannel.receiveAsFlow()

    init {
        Timber.d(args.toString())
        loadMovieInfo()
    }

    private fun loadMovieInfo() =
        viewModelScope.launch {
            loadMovieInfoUseCase(args.movieId).run {
                eventChannel.send(Event.MovieInfoLoaded(this))
            }
        }
}
