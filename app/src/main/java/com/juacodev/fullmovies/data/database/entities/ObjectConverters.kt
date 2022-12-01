package com.juacodev.fullmovies.data.database.entities

import androidx.room.TypeConverter

class ObjectConverters {
    @TypeConverter
    fun fromList(list: List<Int>?): String? {
        return list?.joinToString(",")
    }

    @TypeConverter
    fun toList(string: String?): List<Int>? {
        return string?.split(",")?.map { it.toInt() }
    }
}