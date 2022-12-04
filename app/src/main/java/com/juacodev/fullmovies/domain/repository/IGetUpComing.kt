package com.juacodev.fullmovies.domain.repository

import com.juacodev.fullmovies.data.network.Either
import com.juacodev.fullmovies.data.network.Failure
import com.juacodev.fullmovies.data.network.response.MoviesResponse
import kotlinx.coroutines.flow.Flow

interface IGetUpComingMovies {
    suspend fun getUpCommingMovies(page:Int): Flow<Either<Failure, MoviesResponse>>
}