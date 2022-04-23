package com.redcatgames.musiclib.data.repository

import com.redcatgames.musiclib.data.source.local.dao.ArtistDao
import com.redcatgames.musiclib.data.source.local.mapper.toEntity
import com.redcatgames.musiclib.data.source.local.mapper.toRecord
import com.redcatgames.musiclib.domain.model.Artist
import com.redcatgames.musiclib.domain.repository.ArtistRepository
import java.util.*

class ArtistRepositoryImpl(
    private val artistDao: ArtistDao
) : ArtistRepository {

    init {
        artistDao.deleteAll()
        artistDao.insert(Artist(1, "Ivanov", Calendar.getInstance().time).toEntity())
        artistDao.insert(Artist(2, "Petrov", Calendar.getInstance().time).toEntity())
        artistDao.insert(Artist(3, "Sidorov", Calendar.getInstance().time).toEntity())
    }

    override fun getArtistList(): List<Artist> {
        return artistDao.loadAll().map { it.toRecord() }
    }

    override fun getArtist(id: Long): Artist {
        return artistDao.loadOneByArtistId(id)?.toRecord()
            ?: throw RuntimeException("Element with id $id not found")
    }
}