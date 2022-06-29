package com.redcatgames.movies.data.local.source.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.redcatgames.movies.data.local.source.entity.DictionaryEntity

@Dao
interface DictionaryDao {

    @Query("SELECT * FROM dictionary LIMIT 1") fun first(): LiveData<DictionaryEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(entity: DictionaryEntity): Long

    @Delete suspend fun delete(entity: DictionaryEntity)

    @Query("DELETE FROM dictionary") suspend fun deleteAll()

    @Transaction
    suspend fun replace(entity: DictionaryEntity) {
        deleteAll()
        insert(entity)
    }

    @Query("SELECT COUNT(1) FROM dictionary") suspend fun getCount(): Int
}
