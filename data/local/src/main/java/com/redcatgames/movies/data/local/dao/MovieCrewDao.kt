package com.redcatgames.movies.data.local.dao

import androidx.room.*
import com.redcatgames.movies.data.local.entity.MovieCrewEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface MovieCrewDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(movieCrew: MovieCrewEntity): Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(movieCrews: List<MovieCrewEntity>)

    @Delete
    suspend fun delete(movieCrew: MovieCrewEntity)

    @Query("DELETE FROM movie_crews")
    suspend fun deleteAll()

    @Query("DELETE FROM movie_crews WHERE movieId = :movieId")
    suspend fun deleteByMovie(movieId: Long)

    @Query("DELETE FROM movie_crews WHERE movieId IN (:movieIds)")
    suspend fun deleteByMovieList(movieIds: List<Long>)

    @Update
    suspend fun update(movieCrew: MovieCrewEntity)

    @Query("SELECT * FROM movie_crews")
    fun all(): Flow<List<MovieCrewEntity>>

    @Query("SELECT * FROM movie_crews WHERE movieId = :movieId")
    fun byMovie(movieId: Long): Flow<List<MovieCrewEntity>>

    @Query("SELECT COUNT(1) FROM movie_crews")
    suspend fun getCount(): Int

    @Transaction
    suspend fun replace(movieId: Long, movieCrews: List<MovieCrewEntity>) {
        deleteByMovie(movieId)
        insertAll(movieCrews)
    }
}
