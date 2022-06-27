package com.redcatgames.movies.data.source.local.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.redcatgames.movies.data.source.local.entity.MovieCastEntity

@Dao
interface MovieCastDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(movieCast: MovieCastEntity): Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(movieCasts: List<MovieCastEntity>)

    @Delete suspend fun delete(movieCast: MovieCastEntity)

    @Query("DELETE FROM movie_casts") suspend fun deleteAll()

    @Query("DELETE FROM movie_casts WHERE movieId = :movieId")
    suspend fun deleteByMovie(movieId: Long)

    @Query("DELETE FROM movie_casts WHERE movieId IN (:movieIds)")
    suspend fun deleteByMovieList(movieIds: List<Long>)

    @Update suspend fun update(movieCast: MovieCastEntity)

    @Query("SELECT * FROM movie_casts") fun getAll(): LiveData<List<MovieCastEntity>>

    @Query("SELECT * FROM movie_casts WHERE movieId = :movieId")
    fun getByMovie(movieId: Long): LiveData<List<MovieCastEntity>>

    @Query("SELECT COUNT(1) FROM movie_casts") suspend fun getCount(): Int

    @Transaction
    suspend fun replace(movieId: Long, movieCasts: List<MovieCastEntity>) {
        deleteByMovie(movieId)
        insertAll(movieCasts)
    }
}
