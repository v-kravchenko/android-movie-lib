package com.redcatgames.movies.presentation.home

import android.content.Context
import androidx.lifecycle.viewModelScope
import com.redcatgames.movies.domain.usecase.config.GetImageConfigUseCase
import com.redcatgames.movies.domain.usecase.dictionary.LoadDictionaryUseCase
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
    imageConfigUseCase: GetImageConfigUseCase,
    private val loadDictionaryUseCase: LoadDictionaryUseCase,
    private val deleteAllMoviesUseCase: DeleteAllMoviesUseCase
) : BaseViewModel(appContext) {

    val imageConfig = imageConfigUseCase()
    val loadDictionaryEvent = SingleLiveEvent<UseCaseResult<Unit, String?>>()
    val deleteAllMoviesEvent = SingleLiveEvent<UseCaseResult<Int, Unit>>()

    init {
        viewModelScope.launch {
            loadDictionaryUseCase().run {
                loadDictionaryEvent.postValue(this)
            }
        }
    }

    fun deleteAllMovies() {
        viewModelScope.launch {
            deleteAllMoviesUseCase().run {
                deleteAllMoviesEvent.postValue(this)
            }
        }
    }
}