package com.juacodev.fullmovies.data.network

import com.juacodev.fullmovies.data.network.data.VideosResponse
import com.juacodev.fullmovies.data.network.response.MoviesResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MoviesApi {
    @GET("movie/top_rated?language=en-US")
    suspend fun getTopRatedMovies(
        @Query("page") page:Int
    ): Response<MoviesResponse>
    @GET("movie/upcoming?language=en-US")
    suspend fun getUpcomingMovies(
        @Query("page") page:Int
    ): Response<MoviesResponse>
    @GET("movie/{movie_id}/recommendations?language=en-US")
    suspend fun getRecomendationsMovies(
        @Path("movie_id") movieId:Int,
        @Query("page") page:Int
    ): Response<MoviesResponse>
    @GET("movie/{movie_id}/videos?language=en-US")
    suspend fun getVideos(
        @Path("movie_id") movieId:Int
    ): Response<VideosResponse>
}