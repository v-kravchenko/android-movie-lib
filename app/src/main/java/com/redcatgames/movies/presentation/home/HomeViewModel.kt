package com.redcatgames.movies.presentation.home

import android.content.Context
import androidx.lifecycle.Transformations
import androidx.lifecycle.viewModelScope
import com.redcatgames.movies.domain.model.Language
import com.redcatgames.movies.domain.usecase.config.GetImageConfigUseCase
import com.redcatgames.movies.domain.usecase.config.GetUserConfigUseCase
import com.redcatgames.movies.domain.usecase.config.SetUserConfigApiLanguageUseCase
import com.redcatgames.movies.domain.usecase.dictionary.DeleteDictionaryUseCase
import com.redcatgames.movies.domain.usecase.dictionary.GetLanguageUseCase
import com.redcatgames.movies.domain.usecase.dictionary.GetLanguagesUseCase
import com.redcatgames.movies.domain.usecase.dictionary.LoadDictionaryUseCase
import com.redcatgames.movies.domain.usecase.movie.DeleteAllMoviesUseCase
import com.redcatgames.movies.domain.util.UseCaseResult
import com.redcatgames.movies.presentation.base.BaseViewModel
import com.redcatgames.movies.presentation.util.SingleLiveEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    @ApplicationContext appContext: Context,
    userConfigUseCase: GetUserConfigUseCase,
    imageConfigUseCase: GetImageConfigUseCase,
    languagesUseCase: GetLanguagesUseCase,
    languageUseCase: GetLanguageUseCase,
    private val setUserConfigApiLanguageUseCase: SetUserConfigApiLanguageUseCase,
    private val loadDictionaryUseCase: LoadDictionaryUseCase,
    private val deleteDictionaryUseCase: DeleteDictionaryUseCase,
    private val deleteAllMoviesUseCase: DeleteAllMoviesUseCase
) : BaseViewModel(appContext) {

    val languages = languagesUseCase()
    val imageConfig = imageConfigUseCase()
    val loadDictionaryEvent = SingleLiveEvent<UseCaseResult<Unit, String?>>()
    val deleteAllMoviesEvent = SingleLiveEvent<UseCaseResult<Int, Unit>>()

    val language = Transformations.switchMap(userConfigUseCase()) {
        Timber.d("Current language is ${it.apiLanguage}")
        languageUseCase(it.apiLanguage)
    }

    init {
        loadDictionary()
    }

    private fun loadDictionary() = viewModelScope.launch {
        loadDictionaryUseCase().run {
            loadDictionaryEvent.postValue(this)
        }
    }

    fun deleteDictionary() = viewModelScope.launch {
        deleteDictionaryUseCase()
        loadDictionary()
    }

    fun deleteAllMovies() = viewModelScope.launch {
        deleteAllMoviesUseCase().run {
            deleteAllMoviesEvent.postValue(this)
        }
    }

    fun putLanguage(language: Language) = viewModelScope.launch {
        setUserConfigApiLanguageUseCase(language.iso)
    }
}