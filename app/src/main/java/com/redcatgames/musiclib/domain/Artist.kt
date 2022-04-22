package com.redcatgames.musiclib.domain

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Artist(
    val id: Int,
    val name: String
) : Parcelable
