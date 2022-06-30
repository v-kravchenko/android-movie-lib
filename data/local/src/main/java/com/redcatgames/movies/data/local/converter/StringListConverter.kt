package com.redcatgames.movies.data.local.converter

import androidx.room.TypeConverter
import com.redcatgames.movies.util.fromList
import com.redcatgames.movies.util.toList

object StringListConverter {

    private const val SEPARATOR = ","

    @TypeConverter
    fun toList(value: String): List<String> {
        return value.toList(SEPARATOR)
    }

    @TypeConverter
    fun fromList(list: List<String>): String {
        return list.fromList(SEPARATOR)
    }
}
