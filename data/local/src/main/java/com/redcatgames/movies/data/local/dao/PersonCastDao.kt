package com.redcatgames.movies.data.local.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.redcatgames.movies.data.local.entity.PersonCastEntity

@Dao
interface PersonCastDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(personCast: PersonCastEntity): Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(personCasts: List<PersonCastEntity>)

    @Delete
    suspend fun delete(personCast: PersonCastEntity)

    @Query("DELETE FROM person_casts")
    suspend fun deleteAll()

    @Query("DELETE FROM person_casts WHERE personId = :personId")
    suspend fun deleteByPerson(personId: Long)

    @Query("DELETE FROM person_casts WHERE personId IN (:personIds)")
    suspend fun deleteByPersonList(personIds: List<Long>)

    @Update
    suspend fun update(personCast: PersonCastEntity)

    @Query("SELECT * FROM person_casts")
    fun all(): LiveData<List<PersonCastEntity>>

    @Query("SELECT * FROM person_casts WHERE personId = :personId")
    fun byPerson(personId: Long): LiveData<List<PersonCastEntity>>

    @Query("SELECT COUNT(1) FROM person_casts")
    suspend fun getCount(): Int

    @Transaction
    suspend fun replace(personId: Long, personCasts: List<PersonCastEntity>) {
        deleteByPerson(personId)
        insertAll(personCasts)
    }
}
