package com.juacodev.fullmovies.domain.usecases

import com.juacodev.fullmovies.data.network.Either
import com.juacodev.fullmovies.data.network.Failure
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.Flow

abstract class BaseUseCaseDouble <in Params, in Params2, out Type> where Type : Any  {
    abstract suspend fun run(params: Params, params2: Params2): Flow<Either<Failure, Type>>

    open operator fun invoke(
        job: Job,
        params: Params,
        params2: Params2,
        onResult: (Either<Failure, Type>) -> Unit = {}
    ) {
        val backgroundJob = CoroutineScope(job + Dispatchers.IO).async { run(params, params2) }
        CoroutineScope(job + Dispatchers.Main).launch {
            val await = backgroundJob.await()
            await.collect { d -> onResult(d) }
        }
    }
}