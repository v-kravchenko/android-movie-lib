package com.redcatgames.musiclib.data

import com.redcatgames.musiclib.domain.Artist
import com.redcatgames.musiclib.domain.ArtistRepository

object ArtistRepositoryImpl : ArtistRepository {

    private val artistList = listOf(
        Artist(1, "Ivanov"),
        Artist(2, "Petrov"),
        Artist(3, "Sidorov")
    )

    override fun getArtistList(): List<Artist> {
        return artistList.toList()
    }

    override fun getArtist(id: Int): Artist {
        return artistList.find { it.id == id }
            ?: throw RuntimeException("Element with id $id not found")
    }
}