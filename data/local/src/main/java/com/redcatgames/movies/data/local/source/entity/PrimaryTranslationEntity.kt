package com.redcatgames.movies.data.local.source.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity(tableName = "primary_translations")
data class PrimaryTranslationEntity(@PrimaryKey val name: String, val created: Date)
