package com.redcatgames.movies.presentation.person

import android.content.Context
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.redcatgames.movies.domain.usecase.person.GetPersonUseCase
import com.redcatgames.movies.domain.usecase.person.LoadPersonUseCase
import com.redcatgmes.movies.baseui.BaseViewModel
import com.redcatgmes.movies.baseui.util.SingleLiveEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PersonViewModel
@Inject
constructor(
    @ApplicationContext appContext: Context,
    savedStateHandle: SavedStateHandle,
    getPersonUseCase: GetPersonUseCase,
    private val loadPersonUseCase: LoadPersonUseCase
) : BaseViewModel(appContext) {

    private val args = PersonFragmentArgs.fromSavedStateHandle(savedStateHandle)
    val personInfo = getPersonUseCase(args.personId)
    val loadPersonEvent = SingleLiveEvent<Result<Unit>>()

    init {
        loadPersonUseCase()
    }

    private fun loadPersonUseCase() =
        viewModelScope.launch {
            loadPersonUseCase(args.personId).run { loadPersonEvent.postValue(this) }
        }
}
