package com.redcatgames.movies.data.local.dao

import androidx.room.*
import com.redcatgames.movies.data.local.entity.PersonCrewEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface PersonCrewDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(personCrew: PersonCrewEntity): Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(personCrews: List<PersonCrewEntity>)

    @Delete
    suspend fun delete(personCrew: PersonCrewEntity)

    @Query("DELETE FROM person_crews")
    suspend fun deleteAll()

    @Query("DELETE FROM person_crews WHERE personId = :personId")
    suspend fun deleteByPerson(personId: Long)

    @Query("DELETE FROM person_crews WHERE personId IN (:personIds)")
    suspend fun deleteByPersonList(personIds: List<Long>)

    @Update
    suspend fun update(personCrew: PersonCrewEntity)

    @Query("SELECT * FROM person_crews")
    fun all(): Flow<List<PersonCrewEntity>>

    @Query("SELECT * FROM person_crews WHERE personId = :personId")
    fun byPerson(personId: Long): Flow<List<PersonCrewEntity>>

    @Query("SELECT COUNT(1) FROM person_crews")
    suspend fun getCount(): Int

    @Transaction
    suspend fun replace(personId: Long, personCrews: List<PersonCrewEntity>) {
        deleteByPerson(personId)
        insertAll(personCrews)
    }
}
