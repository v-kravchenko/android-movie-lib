package com.redcatgames.movies.presentation.person

import android.content.Context
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.redcatgames.movies.domain.usecase.person.GetPersonUseCase
import com.redcatgames.movies.domain.usecase.person.LoadPersonUseCase
import com.redcatgmes.movies.baseui.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
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

    sealed class Event {
        data class PersonLoaded(val result: Result<Unit>) : Event()
    }

    private val args = PersonFragmentArgs.fromSavedStateHandle(savedStateHandle)
    val personInfo = getPersonUseCase(args.personId)

    private val eventChannel = Channel<Event>(Channel.BUFFERED)
    val events = eventChannel.receiveAsFlow()

    init {
        loadPersonUseCase()
    }

    private fun loadPersonUseCase() =
        viewModelScope.launch {
            loadPersonUseCase(args.personId).run {
                eventChannel.send(Event.PersonLoaded(this))
            }
        }
}
