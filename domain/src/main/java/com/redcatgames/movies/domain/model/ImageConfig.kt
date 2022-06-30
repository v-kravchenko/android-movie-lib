package com.redcatgames.movies.domain.model

import com.redcatgames.movies.util.EMPTY_STRING

data class ImageConfig(
    val baseUrl: String,
    val secureBaseUrl: String,
    val backdropSizes: List<String>,
    val logoSizes: List<String>,
    val posterSizes: List<String>,
    val profileSizes: List<String>,
    val stillSizes: List<String>
) {
    companion object {
        val EMPTY =
            ImageConfig(
                EMPTY_STRING,
                EMPTY_STRING,
                listOf(),
                listOf(),
                listOf(),
                listOf(),
                listOf()
            )
    }
}
