package com.redcatgames.musiclib.presentation.home

import android.app.Application
import android.content.Context
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.redcatgames.musiclib.domain.model.Artist
import com.redcatgames.musiclib.domain.usecase.DeleteAllArtistUseCase
import com.redcatgames.musiclib.domain.usecase.GetArtistListUseCase
import com.redcatgames.musiclib.domain.usecase.GetArtistUseCase
import com.redcatgames.musiclib.domain.usecase.PutArtistUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    @ApplicationContext appContext: Context,
    private val deleteAllArtistUseCase: DeleteAllArtistUseCase,
    private val putArtistUseCase: PutArtistUseCase,
    private val getArtistListUseCase: GetArtistListUseCase,
    private val getArtistUseCase: GetArtistUseCase
): AndroidViewModel(appContext as Application) {

    val artistList = getArtistListUseCase()

    init {
        viewModelScope.launch {
            deleteAllArtistUseCase()
            putArtistUseCase(Artist(name = "Ivanov"))
            putArtistUseCase(Artist(name = "Petrov"))
            putArtistUseCase(Artist(name = "Sidorov"))
            putArtistUseCase(Artist(name = "Cherkasov"))
        }
    }
}