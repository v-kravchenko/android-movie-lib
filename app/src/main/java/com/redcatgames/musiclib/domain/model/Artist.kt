package com.redcatgames.musiclib.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import java.util.*

@Parcelize
data class Artist(
    val id: Long,
    val name: String,
    val created: Date
) : Parcelable
