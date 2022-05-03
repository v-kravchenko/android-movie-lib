package com.redcatgames.movies.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import java.util.*

@Parcelize
data class Timezone(
    val iso : String,
    val zoneName : String,
    val created: Date
) : Parcelable
