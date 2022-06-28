package com.redcatgmes.movies.baseui

import android.app.Application
import android.content.Context
import androidx.lifecycle.AndroidViewModel

open class BaseViewModel(appContext: Context) : AndroidViewModel(appContext as Application)
