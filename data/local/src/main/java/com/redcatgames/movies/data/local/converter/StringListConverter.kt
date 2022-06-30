package com.redcatgames.movies.data.local.converter

import androidx.room.TypeConverter

object StringListConverter {

    private const val SEPARATOR = ","

    @TypeConverter
    fun toList(value: String): List<String> {
        return value.split(SEPARATOR)
    }

    @TypeConverter
    fun fromList(list: List<String>): String {
        return list.joinToString(separator = SEPARATOR)
    }
}
