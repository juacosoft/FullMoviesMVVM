package com.juacodev.fullmovies.data.network.response

import com.juacodev.fullmovies.data.network.data.MovieModel
import com.juacodev.fullmovies.domain.models.DMoviesResponse

data class MoviesResponse (
    val page: Int,
    val results: List<MovieModel>,
    val total_pages: Int,
    val total_results: Int
){
    fun toDomain() = DMoviesResponse(
        page = page,
        results = results.map { it.toDomain() },
        total_pages = total_pages,
        total_results = total_results
    )
}