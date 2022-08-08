package com.redcatgames.movies.data.local.embedded

import androidx.room.Embedded
import androidx.room.Relation
import com.redcatgames.movies.data.local.entity.PersonCastEntity
import com.redcatgames.movies.data.local.entity.PersonCrewEntity
import com.redcatgames.movies.data.local.entity.PersonEntity

data class PersonInfoEntity(
    @Embedded val person: PersonEntity,
    @Relation(parentColumn = "id", entityColumn = "personId") val casts: List<PersonCastEntity>,
    @Relation(parentColumn = "id", entityColumn = "personId") val crews: List<PersonCrewEntity>,
)
