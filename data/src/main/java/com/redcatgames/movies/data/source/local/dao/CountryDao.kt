package com.redcatgames.movies.data.source.local.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.redcatgames.movies.data.source.local.entity.CountryEntity

@Dao
interface CountryDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(country: CountryEntity): Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(countries: List<CountryEntity>)

    @Delete suspend fun delete(country: CountryEntity)

    @Query("DELETE FROM countries") suspend fun deleteAll()

    @Update suspend fun update(country: CountryEntity)

    @Query("SELECT * FROM countries") fun all(): LiveData<List<CountryEntity>>

    @Query("SELECT COUNT(1) FROM countries") suspend fun getCount(): Int
}
