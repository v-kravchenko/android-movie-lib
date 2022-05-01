package com.redcatgames.movies.data.source.local.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.redcatgames.movies.data.source.local.entity.ImageConfigEntity

@Dao
interface ImageConfigDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(imageConfigEntity: ImageConfigEntity): Long

    @Query("DELETE FROM image_config")
    suspend fun delete()

    @Update
    suspend fun update(imageConfigEntity: ImageConfigEntity)

    @Query("SELECT * FROM image_config LIMIT 1")
    fun get(): LiveData<ImageConfigEntity?>

    @Query("SELECT COUNT(1) FROM image_config")
    suspend fun getCount(): Int
}