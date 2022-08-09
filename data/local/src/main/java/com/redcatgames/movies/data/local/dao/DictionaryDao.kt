package com.redcatgames.movies.data.local.dao

import androidx.room.*
import com.redcatgames.movies.data.local.entity.DictionaryEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface DictionaryDao {

    @Query("SELECT * FROM dictionary LIMIT 1")
    fun first(): Flow<DictionaryEntity?>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(entity: DictionaryEntity): Long

    @Delete
    suspend fun delete(entity: DictionaryEntity)

    @Query("DELETE FROM dictionary")
    suspend fun deleteAll()

    @Transaction
    suspend fun replace(entity: DictionaryEntity) {
        deleteAll()
        insert(entity)
    }

    @Query("SELECT COUNT(1) FROM dictionary")
    suspend fun getCount(): Int
}
