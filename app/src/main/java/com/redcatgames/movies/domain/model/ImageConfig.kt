package com.redcatgames.movies.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import java.util.*

@Parcelize
data class ImageConfig(
    val id : Long,
    val baseUrl : String,
    val secureBaseUrl : String,
    val backdropSizes : List<String>,
    val logoSizes : List<String>,
    val posterSizes : List<String>,
    val profileSizes : List<String>,
    val stillSizes : List<String>,
    val created: Date
) : Parcelable
