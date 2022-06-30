package com.redcatgames.movies.data.local.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.redcatgames.movies.data.local.entity.GenreEntity

@Dao
interface GenreDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE) suspend fun insert(genre: GenreEntity): Long

    @Insert(onConflict = OnConflictStrategy.REPLACE) suspend fun insertAll(genre: List<GenreEntity>)

    @Delete suspend fun delete(genre: GenreEntity)

    @Query("DELETE FROM genres") suspend fun deleteAll()

    @Update suspend fun update(genre: GenreEntity)

    @Query("SELECT * FROM genres") fun all(): LiveData<List<GenreEntity>>

    @Query("SELECT * FROM genres") suspend fun getAll(): List<GenreEntity>

    @Query("SELECT COUNT(1) FROM genres") suspend fun getCount(): Int
}
