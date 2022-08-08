package com.redcatgames.movies.domain.model

data class PersonInfo(
    val person: Person,
    val casts: List<PersonCast>,
    val crews: List<PersonCrew>,
)
