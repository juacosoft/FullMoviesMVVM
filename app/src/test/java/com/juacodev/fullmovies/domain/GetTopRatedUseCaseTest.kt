package com.juacodev.fullmovies.domain

import com.juacodev.fullmovies.data.network.Either
import com.juacodev.fullmovies.data.network.Failure
import com.juacodev.fullmovies.data.network.data.MovieModel
import com.juacodev.fullmovies.data.network.repository.RemoteMoviesRepository
import com.juacodev.fullmovies.data.network.response.MoviesResponse
import com.juacodev.fullmovies.domain.models.DMovie
import com.juacodev.fullmovies.domain.models.DMoviesResponse
import com.juacodev.fullmovies.domain.usecases.GetTopRatedUseCase
import com.juacodev.fullmovies.utils.UnitTest
import io.mockk.coEvery
import io.mockk.every
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flow
import org.junit.Before
import org.junit.Test

class GetTopRatedUseCaseTest: UnitTest() {
    private lateinit var getTopRatedUseCase: GetTopRatedUseCase


    @MockK
    private lateinit var iRemoteMoviesRepository: RemoteMoviesRepository

    @Before
    fun setUp() {
        getTopRatedUseCase = GetTopRatedUseCase(iRemoteMoviesRepository)
    }

    @ExperimentalCoroutinesApi
    @Test
    fun ` shoul call getTopRatedMovies from repository`() {
        coEvery { iRemoteMoviesRepository.getTopRatedMovies(1) } returns flow {
            emit(
                Either.Right(
                    MoviesResponse(
                        page = 1,
                        results = emptyList<MovieModel>(),
                        total_pages = 1,
                        total_results = 1
                    )
                )
            )
            getTopRatedUseCase.run(1)
            coEvery { iRemoteMoviesRepository.getTopRatedMovies(1) }
        }
    }

}