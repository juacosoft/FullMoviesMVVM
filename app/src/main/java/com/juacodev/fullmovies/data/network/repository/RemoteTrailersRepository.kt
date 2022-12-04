package com.juacodev.fullmovies.data.network.repository

import com.juacodev.fullmovies.data.network.Either
import com.juacodev.fullmovies.data.network.Failure
import com.juacodev.fullmovies.data.network.MoviesApi
import com.juacodev.fullmovies.data.network.data.VideosResponse
import com.juacodev.fullmovies.domain.repository.IGetVideos
import com.juacodev.fullmovies.utils.CODE_INVALID_API_HEY
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import org.json.JSONObject
import javax.inject.Inject

class RemoteTrailersRepository @Inject constructor(
    private val movies: MoviesApi
):IGetVideos {
    override suspend fun getVideos(id: Int): Flow<Either<Failure, VideosResponse>> = flow {
        val result = movies.getVideos(id)
        emit(
            when(result.isSuccessful){
                true->{
                    result.body()?.let {
                        Either.Right(it)
                    }?:Either.Left(Failure.DataError)
                }
                (result.code() == CODE_INVALID_API_HEY) -> {
                    val jsonObj = JSONObject(result.errorBody()!!.charStream().readText())
                    Either.Left(Failure.FeatureFailure(jsonObj.toString()))
                }
                false->Either.Left(Failure.ServerError)
            }
        )
    }.catch { emit(Either.Left(Failure.NetworkConnection)) }

}
