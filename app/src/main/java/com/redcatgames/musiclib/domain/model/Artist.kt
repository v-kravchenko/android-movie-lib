package com.redcatgames.musiclib.domain.model

import android.os.Parcelable
import com.redcatgames.musiclib.util.now
import kotlinx.parcelize.Parcelize
import java.util.*

@Parcelize
data class Artist(
    val id: Long = 0,
    val name: String,
    val created: Date = now()
) : Parcelable
