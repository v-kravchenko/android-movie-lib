package com.redcatgames.movies.presentation.home

import android.content.Context
import com.redcatgmes.movies.baseui.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(@ApplicationContext appContext: Context) :
    BaseViewModel(appContext)
