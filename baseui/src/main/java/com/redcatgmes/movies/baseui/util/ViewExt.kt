package com.redcatgmes.movies.baseui.util

import android.view.View

fun View.visible() {
    visibility = View.VISIBLE
}

fun View.gone() {
    visibility = View.GONE
}

fun View.invisible() {
    visibility = View.INVISIBLE
}

fun View.visibleOrGone(visible: Boolean) {
    if (visible)
        visible()
    else
        gone()
}

fun View.isVisible(): Boolean = visibility == View.VISIBLE