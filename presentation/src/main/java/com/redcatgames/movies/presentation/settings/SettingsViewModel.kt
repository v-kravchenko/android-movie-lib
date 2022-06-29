package com.redcatgames.movies.presentation.settings

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.redcatgames.movies.domain.model.Language
import com.redcatgames.movies.domain.usecase.config.GetUserConfigUseCase
import com.redcatgames.movies.domain.usecase.config.SetUserConfigApiLanguageUseCase
import com.redcatgames.movies.domain.usecase.config.SetUserConfigUiDarkModeUseCase
import com.redcatgames.movies.domain.usecase.dictionary.GetLanguageUseCase
import com.redcatgames.movies.domain.usecase.dictionary.LanguagesUseCase
import com.redcatgmes.movies.baseui.BaseViewModel
import com.redcatgmes.movies.baseui.BaseViewModelState
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import kotlinx.coroutines.launch

@HiltViewModel
class SettingsViewModel
@Inject
constructor(
    @ApplicationContext appContext: Context,
    userConfigUseCase: GetUserConfigUseCase,
    languagesUseCase: LanguagesUseCase,
    private val languageUseCase: GetLanguageUseCase,
    private val setUserConfigApiLanguageUseCase: SetUserConfigApiLanguageUseCase,
    private val setUserConfigUiDarkModeUseCase: SetUserConfigUiDarkModeUseCase
) : BaseViewModel(appContext) {

    sealed class State : BaseViewModelState() {
        object Empty : State()
        data class Data(val language: Language?, val darkMode: Int) : State()
        data class Saved(val language: Language?, val darkMode: Int) : State()

        inline fun onData(callback: (value: Data) -> Unit) {
            if (this is Data) callback(this)
        }
    }

    val state: MutableLiveData<State> = MutableLiveData(State.Empty)
    val languages = languagesUseCase()

    init {
        viewModelScope.launch {
            val userConfig = userConfigUseCase()
            setDataState(userConfig.apiLanguage, userConfig.uiDarkMode)
        }
    }

    private suspend fun setDataState(apiLanguage: String, uiDarkMode: Int) {
        val dataState = State.Data(languageUseCase(apiLanguage), uiDarkMode)
        state.postValue(dataState)
    }

    fun setApiLanguage(apiLanguage: String) {
        viewModelScope.launch {
            state.value?.onData {
                val language = languageUseCase(apiLanguage)
                state.postValue(it.copy(language = language))
            }
        }
    }

    fun setUiDarkMode(uiDarkMode: Int) {
        state.value?.onData { state.postValue(it.copy(darkMode = uiDarkMode)) }
    }

    fun save() {
        viewModelScope.launch {
            state.value?.onData {
                it.language?.let { language -> setUserConfigApiLanguageUseCase(language) }
                setUserConfigUiDarkModeUseCase(it.darkMode)
                state.postValue(State.Saved(it.language, it.darkMode))
            }
        }
    }
}
