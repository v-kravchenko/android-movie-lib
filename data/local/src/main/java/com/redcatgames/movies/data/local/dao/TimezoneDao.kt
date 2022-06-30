package com.redcatgames.movies.data.local.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.redcatgames.movies.data.local.entity.TimezoneEntity

@Dao
interface TimezoneDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(timezone: TimezoneEntity): Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(timezones: List<TimezoneEntity>)

    @Delete suspend fun delete(timezone: TimezoneEntity)

    @Query("DELETE FROM timezones") suspend fun deleteAll()

    @Update suspend fun update(timezone: TimezoneEntity)

    @Query("SELECT * FROM timezones") fun all(): LiveData<List<TimezoneEntity>>

    @Query("SELECT COUNT(1) FROM timezones") suspend fun getCount(): Int
}
