package com.redcatgames.movies.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import java.util.*

@Parcelize
data class Language(
    val iso : String,
    val englishName : String,
    val name : String
) : Parcelable
