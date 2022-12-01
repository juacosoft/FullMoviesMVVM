package com.juacodev.fullmovies.data.database.response

import com.juacodev.fullmovies.data.database.entities.MovieModel

data class MoviesResponse (
    val page: Int,
    val results: List<MovieModel>,
    val total_pages: Int,
    val total_results: Int
)