package com.redcatgames.movies.data.source.local.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.redcatgames.movies.data.source.local.entity.LanguageEntity

@Dao
interface LanguageDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(language: LanguageEntity): Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(languages: List<LanguageEntity>)

    @Delete
    suspend fun delete(language: LanguageEntity)

    @Query("DELETE FROM languages")
    suspend fun deleteAll()

    @Update
    suspend fun update(language: LanguageEntity)

    @Query("SELECT * FROM languages")
    fun getAll(): LiveData<List<LanguageEntity>>

    @Query("SELECT COUNT(1) FROM languages")
    suspend fun getCount(): Int
}