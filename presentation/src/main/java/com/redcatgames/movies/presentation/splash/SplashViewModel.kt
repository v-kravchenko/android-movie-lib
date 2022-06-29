package com.redcatgames.movies.presentation.splash

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.redcatgames.movies.domain.usecase.dictionary.DictionaryInfoUseCase
import com.redcatgames.movies.domain.usecase.dictionary.LoadDictionaryUseCase
import com.redcatgmes.movies.baseui.BaseViewModel
import com.redcatgmes.movies.baseui.BaseViewModelState
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
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

        inline fun onNotLoading(callback: () -> Unit) {
            if (this !is Loading) callback()
        }
    }

    val state: MutableLiveData<State> = MutableLiveData(State.Loading)
    val dictionaryInfo = dictionaryInfoUseCase()

    init {
        loadDictionary()
    }

    fun loadDictionary() =
        viewModelScope.launch {
            state.value?.onNotLoading { state.postValue(State.Loading) }

            loadDictionaryUseCase().run {
                state.postValue(if (isSuccess) State.Success else State.Failed)
            }
        }
}
