package com.redcatgames.movies.data.local.dao

import androidx.room.*
import com.redcatgames.movies.data.local.embedded.MovieInfoEntity
import com.redcatgames.movies.data.local.entity.MovieEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface MovieDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(movie: MovieEntity): Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(movies: List<MovieEntity>)

    @Delete
    suspend fun delete(movie: MovieEntity)

    @Query("DELETE FROM movies")
    suspend fun deleteAll()

    @Update
    suspend fun update(movie: MovieEntity)

    @Query("SELECT * FROM movies where id = :movieId")
    fun byId(movieId: Long): Flow<MovieEntity?>

    @Transaction
    @Query("SELECT * FROM movies where id = :movieId")
    fun infoById(movieId: Long): Flow<MovieInfoEntity?>

    @Query("SELECT * FROM movies")
    fun all(): Flow<List<MovieEntity>>

    @Query("SELECT * FROM movies ORDER BY popularity DESC")
    fun popular(): Flow<List<MovieEntity>>

    @Query("SELECT * FROM movies ORDER BY voteCount DESC")
    fun mostVotes(): Flow<List<MovieEntity>>

    @Query("SELECT COUNT(1) FROM movies")
    suspend fun getCount(): Int

    @Transaction
    suspend fun replace(movies: List<MovieEntity>) {
        // deleteAll()
        insertAll(movies)
    }
}
