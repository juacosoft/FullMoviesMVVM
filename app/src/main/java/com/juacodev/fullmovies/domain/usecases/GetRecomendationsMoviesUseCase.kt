package com.juacodev.fullmovies.domain.usecases

import com.juacodev.fullmovies.data.network.Either
import com.juacodev.fullmovies.data.network.Failure
import com.juacodev.fullmovies.data.network.repository.RemoteRecomendationsMoviesRepository
import com.juacodev.fullmovies.data.network.response.MoviesResponse
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetRecomendationsMoviesUseCase @Inject constructor(
    private val repository: RemoteRecomendationsMoviesRepository
) :BaseUseCaseDouble<Int, Int, MoviesResponse>(){
    override suspend fun run(params: Int, params2: Int): Flow<Either<Failure, MoviesResponse>> =repository.getRecommendationsMovies(params, params2)
}