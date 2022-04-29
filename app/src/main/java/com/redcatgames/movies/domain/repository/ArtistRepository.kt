package com.redcatgames.movies.domain.repository

import androidx.lifecycle.LiveData
import com.redcatgames.movies.domain.model.Artist

interface ArtistRepository {
    fun getArtistList(): LiveData<List<Artist>>
    fun getArtist(id: Long): LiveData<Artist?>
    fun getArtistByName(name: String): LiveData<Artist?>
    suspend fun putArtist(artist: Artist)
    suspend fun deleteAllArtist()
}