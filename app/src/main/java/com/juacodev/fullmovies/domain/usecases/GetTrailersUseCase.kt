package com.juacodev.fullmovies.domain.usecases

import com.juacodev.fullmovies.data.network.Either
import com.juacodev.fullmovies.data.network.Failure
import com.juacodev.fullmovies.data.network.data.VideosResponse
import com.juacodev.fullmovies.data.network.repository.RemoteTrailersRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetTrailersUseCase @Inject constructor(
    private val repository: RemoteTrailersRepository
) :BaseUseCase1Param<Int, VideosResponse>(){
    override suspend fun run(params: Int): Flow<Either<Failure, VideosResponse>> = repository.getVideos(params)
}