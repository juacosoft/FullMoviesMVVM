package com.juacodev.fullmovies.data.network.data


import com.juacodev.fullmovies.domain.models.DMovie


data class MovieModel(
    val adult: Boolean,
    val backdrop_path: String,
    val genre_ids: List<Int>?= listOf(),
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
    )
}
