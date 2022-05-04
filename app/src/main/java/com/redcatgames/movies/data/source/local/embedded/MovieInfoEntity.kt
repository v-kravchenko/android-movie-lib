package com.redcatgames.movies.data.source.local.embedded

import androidx.room.Embedded
import androidx.room.Relation
import com.redcatgames.movies.data.source.local.entity.MovieEntity
import com.redcatgames.movies.data.source.local.entity.MovieGenreEntity

data class MovieInfoEntity(
    @Embedded val movie: MovieEntity,
    @Relation(
        parentColumn = "id",
        entityColumn = "movieId"
    )
    val genres: List<MovieGenreEntity>
)

