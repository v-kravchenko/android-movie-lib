package com.redcatgames.movies.data.source.local.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.redcatgames.movies.data.source.local.embedded.MovieInfoEntity
import com.redcatgames.movies.data.source.local.entity.MovieEntity

@Dao
interface MovieDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE) suspend fun insert(movie: MovieEntity): Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(movies: List<MovieEntity>)

    @Delete suspend fun delete(movie: MovieEntity)

    @Query("DELETE FROM movies") suspend fun deleteAll()

    @Update suspend fun update(movie: MovieEntity)

    @Query("SELECT * FROM movies where id = :movieId")
    fun getById(movieId: Long): LiveData<MovieEntity?>

    @Transaction
    @Query("SELECT * FROM movies where id = :movieId")
    fun getInfoById(movieId: Long): LiveData<MovieInfoEntity?>

    @Query("SELECT * FROM movies where title = :movieTitle")
    fun getByTitle(movieTitle: String): LiveData<MovieEntity?>

    @Query("SELECT * FROM movies") fun getAll(): LiveData<List<MovieEntity>>

    @Query("SELECT * FROM movies ORDER BY popularity DESC")
    fun getPopular(): LiveData<List<MovieEntity>>

    @Query("SELECT COUNT(1) FROM movies") suspend fun getCount(): Int

    @Transaction
    suspend fun replace(movies: List<MovieEntity>) {
        deleteAll()
        insertAll(movies)
    }
}
