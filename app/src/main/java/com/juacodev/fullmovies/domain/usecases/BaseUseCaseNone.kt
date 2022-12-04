package com.juacodev.fullmovies.domain.usecases

import com.juacodev.fullmovies.data.network.Either
import com.juacodev.fullmovies.data.network.Failure
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch


/*No params*/
abstract class BaseUseCaseNone <in Params, out Type> where Type : Any {
    abstract suspend fun run(): Flow<Either<Failure, Type>>

    open operator fun invoke(
        job:Job,
        onResult:(Either<Failure, Type>) -> Unit = {}
    ){
        val backgroundJob = CoroutineScope(job + Dispatchers.IO).async { run() }
        CoroutineScope(job + Dispatchers.Main).launch {
            val await = backgroundJob.await()
            await.catch {
                onResult(Either.Left(Failure.ServerError))
            }.collect { d -> onResult(d) }
        }
    }
}