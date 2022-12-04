package com.juacodev.fullmovies.domain.usecases

import com.juacodev.fullmovies.data.network.Either
import com.juacodev.fullmovies.data.network.Failure
import com.juacodev.fullmovies.data.network.repository.RemoteMoviesRepository
import com.juacodev.fullmovies.data.network.response.MoviesResponse
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetTopRatedUseCase @Inject constructor(
    private val repository: RemoteMoviesRepository
) :BaseUseCase1Param<Int, MoviesResponse>(){
    override suspend fun run(params: Int): Flow<Either<Failure, MoviesResponse>> = repository.getTopRatedMovies(params)
}