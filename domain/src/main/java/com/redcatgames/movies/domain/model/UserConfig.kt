package com.redcatgames.movies.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize data class UserConfig(val apiLanguage: String, val uiDarkMode: Int) : Parcelable
