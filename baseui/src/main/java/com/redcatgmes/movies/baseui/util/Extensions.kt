package com.redcatgmes.movies.baseui.util

import android.widget.ImageView
import coil.load
import coil.request.Disposable
import coil.request.ImageRequest

inline fun ImageView.loadByUrl(
    url: String,
    builder: ImageRequest.Builder.() -> Unit = {}
): Disposable {
    return this.load("https://localhost$url", builder = builder)
}
