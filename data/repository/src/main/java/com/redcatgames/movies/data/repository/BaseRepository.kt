package com.redcatgames.movies.data.repository

import android.content.Context
import androidx.annotation.StringRes

open class BaseRepository(private val appContext: Context) {

    fun getString(@StringRes resId: Int): String {
        return appContext.getString(resId)
    }

    fun getString(resId: Int, vararg formatArgs: Any?): String {
        return appContext.getString(resId, formatArgs)
    }
}