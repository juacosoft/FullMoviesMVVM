package com.juacodev.fullmovies.data.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters

@Entity(tableName = "movies")
data class MovieModel(
    val adult: Boolean,
    val backdrop_path: String,
    @TypeConverters(ObjectConverters::class)
    val genre_ids: List<Int>?= listOf(),
    @PrimaryKey
    val id: Int,
    val original_language: String,
    val original_title: String,
    val overview: String,
    val popularity: Double,
    val poster_path: String,
    val release_date: String,
    val title: String,
    val video: Boolean,
    val vote_average: Double,
    val vote_count: Int
)
