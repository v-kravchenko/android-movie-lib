package com.redcatgames.musiclib.presentation.home

import android.app.Application
import android.content.Context
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.redcatgames.musiclib.domain.usecase.GetArtistListUseCase
import com.redcatgames.musiclib.domain.usecase.GetArtistUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    @ApplicationContext appContext: Context,
    private val getArtistListUseCase: GetArtistListUseCase,
    private val getArtistUseCase: GetArtistUseCase
): AndroidViewModel(appContext as Application) {
    fun test() {
        val artistList = getArtistListUseCase.invoke()
        val artist1 = getArtistUseCase.invoke(1)
        Timber.d("artist count: ${artistList.size}, artist1: $artist1")
    }
}