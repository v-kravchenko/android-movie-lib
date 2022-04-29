package com.redcatgames.movies.presentation.home

import android.content.Context
import androidx.lifecycle.viewModelScope
import com.redcatgames.movies.domain.model.Artist
import com.redcatgames.movies.domain.usecase.*
import com.redcatgames.movies.presentation.base.BaseViewModel
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
    private val getArtistUseCase: GetArtistUseCase,
    private val getArtistByNameUseCase: GetArtistByNameUseCase
) : BaseViewModel(appContext) {

    val artistList = getArtistListUseCase()
    val artistIvanov = getArtistByNameUseCase("Ivanov")

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