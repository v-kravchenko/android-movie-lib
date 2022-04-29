package com.redcatgames.movies.data.source.local.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.redcatgames.movies.data.source.local.entity.MovieEntity

@Dao
interface MovieDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(movie: MovieEntity): Long

    @Delete
    fun delete(movie: MovieEntity)

    @Query("DELETE FROM movies")
    fun deleteAll()

    @Update
    fun update(movie: MovieEntity)

    @Query("SELECT * FROM movies where id = :movieId")
    fun loadById(movieId: Long): LiveData<MovieEntity?>

    @Query("SELECT * FROM movies where title = :movieTitle")
    fun loadByTitle(movieTitle: String): LiveData<MovieEntity?>

    @Query("SELECT * FROM movies")
    fun loadAll(): LiveData<List<MovieEntity>>
}