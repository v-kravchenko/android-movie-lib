package com.redcatgames.movies.data.source.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import java.util.*

@Entity(tableName = "image_config")
data class ImageConfigEntity(
    @PrimaryKey(autoGenerate = true)
    val id : Long,
    val baseUrl : String,
    val secureBaseUrl : String,
    val backdropSizes : List<String>,
    val logoSizes : List<String>,
    val posterSizes : List<String>,
    val profileSizes : List<String>,
    val stillSizes : List<String>,
    val created: Date
)
