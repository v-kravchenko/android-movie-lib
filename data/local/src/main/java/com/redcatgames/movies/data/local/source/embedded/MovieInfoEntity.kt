package com.redcatgames.movies.data.local.source.embedded

import androidx.room.Embedded
import androidx.room.Relation
import com.redcatgames.movies.data.local.source.entity.MovieCastEntity
import com.redcatgames.movies.data.local.source.entity.MovieCrewEntity
import com.redcatgames.movies.data.local.source.entity.MovieEntity
import com.redcatgames.movies.data.local.source.entity.MovieGenreEntity

data class MovieInfoEntity(
    @Embedded val movie: MovieEntity,
    @Relation(parentColumn = "id", entityColumn = "movieId") val genres: List<MovieGenreEntity>,
    @Relation(parentColumn = "id", entityColumn = "movieId") val casts: List<MovieCastEntity>,
    @Relation(parentColumn = "id", entityColumn = "movieId") val crews: List<MovieCrewEntity>
)
