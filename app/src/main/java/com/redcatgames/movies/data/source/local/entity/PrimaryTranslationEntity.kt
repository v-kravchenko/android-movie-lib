package com.redcatgames.movies.data.source.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import java.util.*

@Entity(tableName = "primary_translations")
data class PrimaryTranslationEntity(
    @PrimaryKey
    val name : String,
    val created: Date
)
