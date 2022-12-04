package com.juacodev.fullmovies.domain.usecases

import com.juacodev.fullmovies.data.network.Either
import com.juacodev.fullmovies.data.network.Failure
import com.juacodev.fullmovies.data.network.repository.RemoteUpCommingMoviesRepository
import com.juacodev.fullmovies.data.network.response.MoviesResponse
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetUpCommingMoviesUseCase @Inject constructor(
    private val repository: RemoteUpCommingMoviesRepository
):BaseUseCase1Param<Int, MoviesResponse>(){
    override suspend fun run(params: Int): Flow<Either<Failure, MoviesResponse>> =repository.getUpCommingMovies(params)
}