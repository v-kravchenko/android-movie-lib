package com.redcatgames.movies.presentation.settings

import android.content.Context
import androidx.lifecycle.Transformations
import androidx.lifecycle.viewModelScope
import com.redcatgames.movies.domain.model.Language
import com.redcatgames.movies.domain.usecase.config.GetUserConfigUseCase
import com.redcatgames.movies.domain.usecase.config.SetUserConfigApiLanguageUseCase
import com.redcatgames.movies.domain.usecase.dictionary.GetLanguageUseCase
import com.redcatgames.movies.domain.usecase.dictionary.GetLanguagesUseCase
import com.redcatgmes.movies.baseui.BaseViewModel
import com.redcatgmes.movies.baseui.util.SingleLiveEvent
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
    languagesUseCase: GetLanguagesUseCase,
    languageUseCase: GetLanguageUseCase,
    private val setUserConfigApiLanguageUseCase: SetUserConfigApiLanguageUseCase
) : BaseViewModel(appContext) {

    val eventSaved = SingleLiveEvent<Unit>()

    val languages = languagesUseCase()

    val language =
        Transformations.switchMap(userConfigUseCase()) { languageUseCase(it.apiLanguage) }

    fun save(currentLanguage: Language?) {

        viewModelScope.launch {
            currentLanguage?.let { setUserConfigApiLanguageUseCase(it) }
            eventSaved.postValue(Unit)
        }
    }
}
