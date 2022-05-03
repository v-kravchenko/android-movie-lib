package com.redcatgames.movies.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import java.util.*

@Parcelize
data class PrimaryTranslation(
    val name : String,
    val created: Date
) : Parcelable
