package com.redcatgames.musiclib.data.source.local.dao

import androidx.room.*
import com.redcatgames.musiclib.data.source.local.entity.ArtistEntity

@Dao
interface ArtistDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(photo: ArtistEntity): Long

    @Delete
    fun delete(photo: ArtistEntity)

    @Query("DELETE FROM artists")
    fun deleteAll()

    @Update
    fun update(artist: ArtistEntity)

    @Query("SELECT * FROM artists where id = :artistId")
    fun loadOneByArtistId(artistId: Long): ArtistEntity?

    @Query("SELECT * FROM artists")
    fun loadAll(): List<ArtistEntity>
}