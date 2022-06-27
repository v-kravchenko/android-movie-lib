package com.redcatgames.movies.data.source.local.converter

import androidx.room.TypeConverter
import java.util.*

object DateConverter {
  @TypeConverter
  fun toDate(dateLong: Long?): Date? {
    return dateLong?.let { Date(it) }
  }

  @TypeConverter
  fun fromDate(date: Date?): Long? {
    return date?.time
  }
}
