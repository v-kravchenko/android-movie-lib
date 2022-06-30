package com.redcatgames.movies.data.local.entity

import androidx.room.Entity
import java.util.*

@Entity(tableName = "dictionary", primaryKeys = ["language", "created"])
data class DictionaryEntity(val language: String, val created: Date)
