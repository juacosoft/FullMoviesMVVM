package com.juacodev.fullmovies.data.network

import com.juacodev.fullmovies.data.database.response.MoviesResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface MoviesApi {
    @GET("movie/top_rated?language=en-US")
    suspend fun getTopRatedMovies(
        @Query("page") page:Int
    ): Response<MoviesResponse>
    @GET("/movie/upcoming?language=en-US")
    suspend fun getUpcomingMovies(
        @Query("page") page:Int
    ): Response<MoviesResponse>
}