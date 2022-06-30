package com.redcatgames.movies.data.local.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.redcatgames.movies.data.local.entity.PrimaryTranslationEntity

@Dao
interface PrimaryTranslationDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(primaryTranslation: PrimaryTranslationEntity): Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(primaryTranslation: List<PrimaryTranslationEntity>)

    @Delete suspend fun delete(primaryTranslation: PrimaryTranslationEntity)

    @Query("DELETE FROM primary_translations") suspend fun deleteAll()

    @Update suspend fun update(primaryTranslation: PrimaryTranslationEntity)

    @Query("SELECT * FROM primary_translations") fun all(): LiveData<List<PrimaryTranslationEntity>>

    @Query("SELECT COUNT(1) FROM primary_translations") suspend fun getCount(): Int

    @Query("SELECT * FROM primary_translations WHERE name LIKE :languageCode || '-%'")
    suspend fun findByLanguage(languageCode: String): PrimaryTranslationEntity?
}
