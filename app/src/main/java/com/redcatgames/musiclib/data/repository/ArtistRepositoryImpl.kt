package com.redcatgames.musiclib.data.repository

import com.redcatgames.musiclib.data.source.local.dao.ArtistDao
import com.redcatgames.musiclib.data.source.local.mapper.toEntity
import com.redcatgames.musiclib.data.source.local.mapper.toRecord
import com.redcatgames.musiclib.domain.model.Artist
import com.redcatgames.musiclib.domain.repository.ArtistRepository

class ArtistRepositoryImpl(
    private val artistDao: ArtistDao
) : ArtistRepository {

    init {
        artistDao.deleteAll()
        artistDao.insert(Artist(name = "Ivanov").toEntity())
        artistDao.insert(Artist(name = "Petrov").toEntity())
        artistDao.insert(Artist(name = "Sidorov").toEntity())
    }

    override fun getArtistList(): List<Artist> {
        return artistDao.loadAll().map { it.toRecord() }
    }

    override fun getArtist(id: Long): Artist {
        return artistDao.loadOneByArtistId(id)?.toRecord()
            ?: throw RuntimeException("Element with id $id not found")
    }
}