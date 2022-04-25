package com.redcatgames.musiclib.data.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.redcatgames.musiclib.data.source.local.dao.ArtistDao
import com.redcatgames.musiclib.data.source.local.mapper.mapFrom
import com.redcatgames.musiclib.data.source.local.mapper.mapTo
import com.redcatgames.musiclib.domain.model.Artist
import com.redcatgames.musiclib.domain.repository.ArtistRepository

class ArtistRepositoryImpl(
    private val artistDao: ArtistDao
) : ArtistRepository {

    override fun getArtistList(): LiveData<List<Artist>> {
        return Transformations.map(artistDao.loadAll()) {
            it.map { entity -> entity.mapFrom() }
        }
    }

    override fun getArtist(id: Long): LiveData<Artist?> {
        return Transformations.map(artistDao.loadOneByArtistId(id)) {
            it?.mapFrom()
        }
    }

    override fun getArtistByName(name: String): LiveData<Artist?> {
        return Transformations.map(artistDao.loadOneByArtistName(name)) {
            it?.mapFrom()
        }
    }

    override suspend fun putArtist(artist: Artist) {
        artistDao.insert(artist.mapTo())
    }

    override suspend fun deleteAllArtist() {
        artistDao.deleteAll()
    }
}