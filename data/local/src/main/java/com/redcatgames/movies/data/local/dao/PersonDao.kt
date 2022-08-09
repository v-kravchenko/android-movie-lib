package com.redcatgames.movies.data.local.dao

import androidx.room.*
import com.redcatgames.movies.data.local.embedded.PersonInfoEntity
import com.redcatgames.movies.data.local.entity.PersonEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface PersonDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(person: PersonEntity): Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(persons: List<PersonEntity>)

    @Delete
    suspend fun delete(person: PersonEntity)

    @Query("DELETE FROM persons")
    suspend fun deleteAll()

    @Update
    suspend fun update(person: PersonEntity)

    @Query("SELECT * FROM persons where id = :personId")
    fun byId(personId: Long): Flow<PersonEntity?>

    @Transaction
    @Query("SELECT * FROM persons where id = :personId")
    fun infoById(personId: Long): Flow<PersonInfoEntity?>

    @Query("SELECT * FROM persons")
    fun all(): Flow<List<PersonEntity>>

    @Query("SELECT COUNT(1) FROM persons")
    suspend fun getCount(): Int
}
