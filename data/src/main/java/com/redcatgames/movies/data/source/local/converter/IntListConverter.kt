package com.redcatgames.movies.data.source.local.converter

import androidx.room.TypeConverter

object IntListConverter {

    private const val SEPARATOR = ","

    @TypeConverter
    fun toList(value: String): List<Int> {
        return value.split(SEPARATOR).map { it.toInt() }
    }

    @TypeConverter
    fun fromList(list: List<Int>): String {
        return list.joinToString(separator = SEPARATOR)
    }
}
