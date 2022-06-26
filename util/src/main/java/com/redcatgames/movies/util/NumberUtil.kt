package com.redcatgames.movies.util

fun Double.format(digits: Int) = "%.${digits}f".format(this)
