package com.redcatgames.movies.presentation.splash

import android.content.Context
import androidx.lifecycle.viewModelScope
import com.redcatgames.movies.domain.usecase.dictionary.DictionaryInfoUseCase
import com.redcatgames.movies.domain.usecase.dictionary.LoadDictionaryUseCase
import com.redcatgmes.movies.baseui.BaseViewModel
import com.redcatgmes.movies.baseui.BaseViewModelState
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

@HiltViewModel
class SplashViewModel
@Inject
constructor(
    @ApplicationContext appContext: Context,
    dictionaryInfoUseCase: DictionaryInfoUseCase,
    private val loadDictionaryUseCase: LoadDictionaryUseCase
) : BaseViewModel(appContext) {

    sealed class State : BaseViewModelState() {
        object Loading : State()
        object Failed : State()
        object Success : State()
    }

    private val _state = MutableStateFlow<State>(State.Loading)
    val state: StateFlow<State> = _state.asStateFlow()

    val dictionaryInfo = dictionaryInfoUseCase()

    init {
        loadDictionary()
    }

    fun loadDictionary() =
        viewModelScope.launch {
            _state.value = State.Loading

            loadDictionaryUseCase().run {
                _state.value = if (isSuccess) State.Success else State.Failed
            }
        }
}
