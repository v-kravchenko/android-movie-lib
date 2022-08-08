package com.redcatgames.movies.presentation

import android.graphics.Point
import com.redcatgames.movies.domain.model.*

private fun interface IPhotoSize {
    fun toUriPath(): String
}

enum class BackdropSize(val size: Point) : IPhotoSize {
    SMALL(Point(300, 169)) {
        override fun toUriPath() = "w300"
    },
    MEDIUM(Point(780, 439)) {
        override fun toUriPath() = "w780"
    },
    LARGE(Point(1280, 720)) {
        override fun toUriPath() = "w1280"
    },
    ORIGINAL(Point(0, 0)) {
        override fun toUriPath() = "original"
    },
}

enum class PosterSize(val size: Point) : IPhotoSize {
    EXTRA_SMALL(Point(154, 231)) {
        override fun toUriPath() = "w154"
    },
    SMALL(Point(185, 278)) {
        override fun toUriPath() = "w185"
    },
    MEDIUM(Point(342, 513)) {
        override fun toUriPath() = "w342"
    },
    LARGE(Point(500, 750)) {
        override fun toUriPath() = "w500"
    },
    EXTRA_LARGE(Point(780, 1170)) {
        override fun toUriPath() = "w780"
    },
    ORIGINAL(Point(0, 0)) {
        override fun toUriPath() = "original"
    },
}

enum class ProfileSize(val size: Point) : IPhotoSize {
    SMALL(Point(45, 68)) {
        override fun toUriPath() = "w45"
    },
    MEDIUM(Point(185, 278)) {
        override fun toUriPath() = "w185"
    },
    LARGE(Point(421, 632)) {
        override fun toUriPath() = "h632"
    },
    ORIGINAL(Point(0, 0)) {
        override fun toUriPath() = "original"
    },
}

fun MovieCast.getProfileUri(profileSize: ProfileSize): String =
    profileSize.toUriPath() + profilePath

fun MovieCrew.getProfileUri(profileSize: ProfileSize): String =
    profileSize.toUriPath() + profilePath

fun Movie.getPosterUri(posterSize: PosterSize): String = posterSize.toUriPath() + posterPath

fun Movie.getBackdropUri(backdropSize: BackdropSize): String =
    backdropSize.toUriPath() + backdropPath

fun Person.getProfileUri(profileSize: ProfileSize): String = profileSize.toUriPath() + profilePath

fun PersonCast.getPosterUri(posterSize: PosterSize): String = posterSize.toUriPath() + posterPath

fun PersonCrew.getPosterUri(posterSize: PosterSize): String = posterSize.toUriPath() + posterPath
