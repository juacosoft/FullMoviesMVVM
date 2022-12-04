package com.juacodev.fullmovies.viewmodels

import com.google.common.truth.Truth
import com.juacodev.fullmovies.data.network.Either
import com.juacodev.fullmovies.data.network.Failure
import com.juacodev.fullmovies.data.network.data.MovieModel
import com.juacodev.fullmovies.data.network.response.MoviesResponse
import com.juacodev.fullmovies.domain.LocalRepository
import com.juacodev.fullmovies.domain.models.DMovie
import com.juacodev.fullmovies.domain.usecases.GetRecomendationsMoviesUseCase
import com.juacodev.fullmovies.domain.usecases.GetTopRatedUseCase
import com.juacodev.fullmovies.domain.usecases.GetUpCommingMoviesUseCase
import com.juacodev.fullmovies.ui.activities.fragments.homefragment.viewmodel.HomeViewModel
import com.juacodev.fullmovies.utils.UnitTest
import com.juacodev.fullmovies.utils.getOrAwaitValueTest
import io.mockk.every
import io.mockk.impl.annotations.MockK
import org.junit.Before
import org.junit.Test


class HomeViewModelTest :UnitTest() {
    private lateinit var homeViewModel: HomeViewModel
    @MockK
    private lateinit var getTopRatedUseCase: GetTopRatedUseCase
    @MockK
    private lateinit var getUpCommingMoviesUseCase: GetUpCommingMoviesUseCase
    @MockK
    private lateinit var getRecomendationsMoviesUseCase: GetRecomendationsMoviesUseCase
    @MockK
    private lateinit var localRepository: LocalRepository

    private lateinit var results: List<MovieModel>
    private lateinit var moviesResponse: MoviesResponse


    @Before
    fun setUp() {
        results = listOf(
            MovieModel(
                id = 1,
                title = "title",
                overview = "overview",
                poster_path = "poster_path",
                backdrop_path = "backdrop_path",
                release_date = "release_date",
                vote_average = 1.0,
                vote_count = 1,
                popularity = 1.0,
                original_language = "original_language",
                original_title = "original_title",
                genre_ids = emptyList(),
                video = false,
                adult = false,
            )
        )
        moviesResponse = MoviesResponse(
            page = 1,
            results = results,
            total_pages = 1,
            total_results = 1
        )
        homeViewModel = HomeViewModel(
            getTopRatedUseCase,
            getUpCommingMoviesUseCase,
            getRecomendationsMoviesUseCase,
            localRepository
        )
    }

    @Test
    fun `should return a list of movies top rate use case`() {
        every { getTopRatedUseCase(any(),any()) }.answers {
            lastArg<(Either<Failure, MoviesResponse>) -> Unit>()(Either.Right(moviesResponse))
        }
        homeViewModel.syncGetTopRate()
        val response = homeViewModel.toRatedMovies.getOrAwaitValueTest()
        Truth.assertThat(response.error).isNull()
    }
    @Test
    fun `should network error when get top rate movies`() {
        every { getTopRatedUseCase(any(),any()) }.answers {
            lastArg<(Either<Failure, MoviesResponse>) -> Unit>()(Either.Left(Failure.NetworkConnection))
        }
        homeViewModel.syncGetTopRate()
        val response = homeViewModel.toRatedMovies.getOrAwaitValueTest()
        Truth.assertThat(response.data).isNull()
    }
}