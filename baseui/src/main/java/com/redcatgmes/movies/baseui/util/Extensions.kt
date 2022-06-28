package com.redcatgmes.movies.baseui.util

import android.widget.ImageView
import coil.load
import coil.request.Disposable

fun ImageView.loadByUrl(url: String): Disposable {
    return this.load("https://localhost/$url")
}
