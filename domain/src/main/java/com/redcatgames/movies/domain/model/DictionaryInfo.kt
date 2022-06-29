package com.redcatgames.movies.domain.model

import android.os.Parcelable
import java.util.*
import kotlinx.parcelize.Parcelize

@Parcelize data class DictionaryInfo(val language: String, val created: Date) : Parcelable
