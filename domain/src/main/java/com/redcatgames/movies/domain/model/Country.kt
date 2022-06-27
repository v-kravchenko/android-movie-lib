package com.redcatgames.movies.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Country(val iso: String, val englishName: String, val nativeName: String) : Parcelable
