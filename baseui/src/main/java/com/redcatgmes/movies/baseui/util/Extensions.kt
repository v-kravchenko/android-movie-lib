package com.redcatgmes.movies.baseui.util

import android.widget.ImageView
import androidx.annotation.DrawableRes
import coil.load
import coil.request.Disposable

fun ImageView.loadByUrl(url: String, @DrawableRes drawableResId: Int = 0): Disposable {
    return this.load("https://localhost$url") {
        if (drawableResId > 0) {
            placeholder(drawableResId)
        }
    }
}
