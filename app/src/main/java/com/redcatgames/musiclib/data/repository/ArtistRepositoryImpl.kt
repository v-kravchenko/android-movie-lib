package com.redcatgames.musiclib.data.repository

import com.redcatgames.musiclib.domain.model.Artist
import com.redcatgames.musiclib.domain.repository.ArtistRepository

class ArtistRepositoryImpl : ArtistRepository {

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