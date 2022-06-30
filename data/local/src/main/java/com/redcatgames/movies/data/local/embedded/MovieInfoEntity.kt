package com.redcatgames.movies.data.local.embedded

import androidx.room.Embedded
import androidx.room.Relation
import com.redcatgames.movies.data.local.entity.MovieCastEntity
import com.redcatgames.movies.data.local.entity.MovieCrewEntity
import com.redcatgames.movies.data.local.entity.MovieEntity
import com.redcatgames.movies.data.local.entity.MovieGenreEntity

data class MovieInfoEntity(
    @Embedded val movie: MovieEntity,
    @Relation(parentColumn = "id", entityColumn = "movieId") val genres: List<MovieGenreEntity>,
    @Relation(parentColumn = "id", entityColumn = "movieId") val casts: List<MovieCastEntity>,
    @Relation(parentColumn = "id", entityColumn = "movieId") val crews: List<MovieCrewEntity>
)
