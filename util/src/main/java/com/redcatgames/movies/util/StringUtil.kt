package com.redcatgames.movies.util

val String.Companion.empty: String
    get() = EMPTY_STRING

const val EMPTY_STRING = ""

fun String.toList(separator: String = ","): List<String> {
    return split(separator)
}

fun List<String>.fromList(separator: String = ","): String {
    return joinToString(separator = separator)
}