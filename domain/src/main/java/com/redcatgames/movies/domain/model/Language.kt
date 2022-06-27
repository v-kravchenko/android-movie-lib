package com.redcatgames.movies.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Language(val iso: String, val englishName: String, val name: String) : Parcelable
