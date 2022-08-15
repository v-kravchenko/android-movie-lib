package com.redcatgmes.movies.baseui.util

import android.view.View
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import timber.log.Timber

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

fun RecyclerView.setOnScrolledToEnd(listener: () -> (Unit)) {
    addOnScrollListener(
        object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)

                (layoutManager as? GridLayoutManager)?.let { gridLayoutManager
                    ->
                    val visibleItemCount: Int = gridLayoutManager.childCount
                    val totalItemCount: Int = gridLayoutManager.itemCount
                    val firstVisibleItemPosition =
                        gridLayoutManager.findFirstVisibleItemPosition()

                    if (visibleItemCount + firstVisibleItemPosition >= totalItemCount &&
                        firstVisibleItemPosition >= 0) {
                        listener()
                    }
                }
            }
        })
}