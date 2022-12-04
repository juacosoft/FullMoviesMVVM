package com.juacodev.fullmovies.domain.models

data class DMoviesResponse (
    val page: Int,
    val results: List<DMovie>,
    val total_pages: Int,
    val total_results: Int
)
