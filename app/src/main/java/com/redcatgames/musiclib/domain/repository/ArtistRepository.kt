package com.redcatgames.musiclib.domain.repository

import androidx.lifecycle.LiveData
import com.redcatgames.musiclib.domain.model.Artist

interface ArtistRepository {
    fun getArtistList(): LiveData<List<Artist>>
    fun getArtist(id: Long): LiveData<Artist?>
    suspend fun putArtist(artist: Artist)
    suspend fun deleteAllArtist()
}