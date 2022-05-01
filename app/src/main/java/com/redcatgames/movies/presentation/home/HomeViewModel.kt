package com.redcatgames.movies.presentation.home

import android.content.Context
import androidx.lifecycle.viewModelScope
import com.redcatgames.movies.domain.usecase.movie.DeleteAllMoviesUseCase
import com.redcatgames.movies.domain.util.UseCaseResult
import com.redcatgames.movies.presentation.base.BaseViewModel
import com.redcatgames.movies.presentation.util.SingleLiveEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    @ApplicationContext appContext: Context,
    private val deleteAllMoviesUseCase: DeleteAllMoviesUseCase
) : BaseViewModel(appContext) {

    val deleteAllMoviesEvent = SingleLiveEvent<UseCaseResult<Int>>()

    fun deleteAllMovies() {
        viewModelScope.launch {
            deleteAllMoviesUseCase().run {
                deleteAllMoviesEvent.postValue(this)
            }
        }
    }
}