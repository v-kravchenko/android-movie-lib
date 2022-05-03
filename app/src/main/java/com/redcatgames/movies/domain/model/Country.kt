package com.redcatgames.movies.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import java.util.*

@Parcelize
data class Country(
    val iso : String,
    val englishName : String,
    val nativeName : String,
    val created: Date
) : Parcelable
