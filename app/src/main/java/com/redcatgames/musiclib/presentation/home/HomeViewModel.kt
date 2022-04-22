package com.redcatgames.musiclib.presentation.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class HomeViewModel : ViewModel() {

    init {
        viewModelScope.launch(Dispatchers.IO) {

        }
    }

}