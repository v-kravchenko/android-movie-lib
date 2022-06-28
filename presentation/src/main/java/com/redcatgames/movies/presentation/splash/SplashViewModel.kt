package com.redcatgames.movies.presentation.splash

import android.content.Context
import androidx.lifecycle.viewModelScope
import com.redcatgames.movies.domain.usecase.dictionary.LoadDictionaryUseCase
import com.redcatgames.movies.presentation.base.BaseViewModel
import com.redcatgames.movies.presentation.util.SingleLiveEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import kotlinx.coroutines.launch

@HiltViewModel
class SplashViewModel
@Inject
constructor(
    @ApplicationContext appContext: Context,
    private val loadDictionaryUseCase: LoadDictionaryUseCase,
) : BaseViewModel(appContext) {

    val loadDictionaryEvent = SingleLiveEvent<Result<Unit>>()

    init {
        loadDictionary()
    }

    private fun loadDictionary() =
        viewModelScope.launch {
            loadDictionaryUseCase().run { loadDictionaryEvent.postValue(this) }
        }
}
