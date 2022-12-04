package com.juacodev.fullmovies.data.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.juacodev.fullmovies.data.database.ClasificateMovies
import com.juacodev.fullmovies.domain.models.DMovie

@Entity(tableName = "movies")
class MovieLocalModel(
    val adult: Boolean,
    val backdrop_path: String,
    val genre_ids: List<Int>?= listOf(),
    @PrimaryKey val id: Int,
    val original_language: String,
    val original_title: String,
    val overview: String,
    val popularity: Double,
    val poster_path: String,
    val release_date: String,
    val title: String,
    val video: Boolean,
    val vote_average: Double,
    val vote_count: Int,
    val clasificateMovies: Int
){
    fun toDomain() = DMovie(
        adult = adult,
        backdrop_path = backdrop_path,
        genre_ids = genre_ids?: emptyList(),
        id = id,
        original_language = original_language,
        original_title = original_title,
        overview = overview,
        popularity = popularity,
        poster_path = poster_path,
        release_date = release_date,
        title = title,
        video = video,
        vote_average = vote_average,
        vote_count = vote_count,
        clasificateMovies = clasificateMovies
    )
}
