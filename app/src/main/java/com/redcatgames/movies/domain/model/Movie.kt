package com.redcatgames.movies.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import java.util.*

@Parcelize
data class Movie(
    val id: Long,
    val title: String,
    val overview: String,
    val created: Date
) : Parcelable
