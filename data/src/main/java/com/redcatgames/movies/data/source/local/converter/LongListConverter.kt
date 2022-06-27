package com.redcatgames.movies.data.source.local.converter

import androidx.room.TypeConverter

object LongListConverter {

  private const val SEPARATOR = ","

  @TypeConverter
  fun toList(value: String): List<Long> {
    return value.split(SEPARATOR).map { if (it.isEmpty()) 0 else it.toLong() }
  }

  @TypeConverter
  fun fromList(list: List<Long>): String {
    return list.joinToString(separator = SEPARATOR)
  }
}
