package com.redcatgames.movies.data.local.dao

import androidx.room.*
import com.redcatgames.movies.data.local.entity.CountryEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface CountryDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(country: CountryEntity): Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(countries: List<CountryEntity>)

    @Delete
    suspend fun delete(country: CountryEntity)

    @Query("DELETE FROM countries")
    suspend fun deleteAll()

    @Update
    suspend fun update(country: CountryEntity)

    @Query("SELECT * FROM countries")
    fun all(): Flow<List<CountryEntity>>

    @Query("SELECT COUNT(1) FROM countries")
    suspend fun getCount(): Int
}
