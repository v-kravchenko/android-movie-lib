package com.redcatgames.movies.data.local.dao

import androidx.room.*
import com.redcatgames.movies.data.local.entity.MovieGenreEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface MovieGenreDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(movieGenre: MovieGenreEntity): Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(movieGenres: List<MovieGenreEntity>)

    @Delete
    suspend fun delete(movieGenre: MovieGenreEntity)

    @Query("DELETE FROM movie_genres")
    suspend fun deleteAll()

    @Query("DELETE FROM movie_genres WHERE movieId = :movieId")
    suspend fun deleteByMovie(movieId: Long)

    @Query("DELETE FROM movie_genres WHERE movieId IN (:movieIds)")
    suspend fun deleteByMovieList(movieIds: List<Long>)

    @Update
    suspend fun update(movieGenre: MovieGenreEntity)

    @Query("SELECT * FROM movie_genres")
    fun all(): Flow<List<MovieGenreEntity>>

    @Query("SELECT * FROM movie_genres WHERE movieId = :movieId")
    fun byMovie(movieId: Long): Flow<List<MovieGenreEntity>>

    @Query("SELECT COUNT(1) FROM movie_genres")
    suspend fun getCount(): Int

    @Transaction
    suspend fun replace(movieId: Long, movieGenres: List<MovieGenreEntity>) {
        deleteByMovie(movieId)
        insertAll(movieGenres)
    }

    @Transaction
    suspend fun replace(movieIds: List<Long>, moviesGenres: List<MovieGenreEntity>) {
        deleteByMovieList(movieIds)
        insertAll(moviesGenres)
    }
}
