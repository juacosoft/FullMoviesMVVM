package com.juacodev.fullmovies.domain.repository

import com.juacodev.fullmovies.data.network.Either
import com.juacodev.fullmovies.data.network.Failure
import com.juacodev.fullmovies.data.network.data.VideosResponse
import kotlinx.coroutines.flow.Flow

interface IGetVideos {
    suspend fun getVideos(id: Int): Flow<Either<Failure, VideosResponse>>
}