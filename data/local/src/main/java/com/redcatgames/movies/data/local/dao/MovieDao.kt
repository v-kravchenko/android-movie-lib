package com.redcatgames.movies.data.local.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.redcatgames.movies.data.local.embedded.MovieInfoEntity
import com.redcatgames.movies.data.local.entity.MovieEntity

@Dao
interface MovieDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE) suspend fun insert(movie: MovieEntity): Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(movies: List<MovieEntity>)

    @Delete suspend fun delete(movie: MovieEntity)

    @Query("DELETE FROM movies") suspend fun deleteAll()

    @Update suspend fun update(movie: MovieEntity)

    @Query("SELECT * FROM movies where id = :movieId")
    fun byId(movieId: Long): LiveData<MovieEntity?>

    @Transaction
    @Query("SELECT * FROM movies where id = :movieId")
    fun infoById(movieId: Long): LiveData<MovieInfoEntity?>

    @Query("SELECT * FROM movies") fun all(): LiveData<List<MovieEntity>>

    @Query("SELECT * FROM movies ORDER BY popularity DESC")
    fun popular(): LiveData<List<MovieEntity>>

    @Query("SELECT COUNT(1) FROM movies") suspend fun getCount(): Int

    @Transaction
    suspend fun replace(movies: List<MovieEntity>) {
        deleteAll()
        insertAll(movies)
    }
}
