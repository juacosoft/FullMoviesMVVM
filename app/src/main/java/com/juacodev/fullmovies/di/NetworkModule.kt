package com.juacodev.fullmovies.di

import com.juacodev.fullmovies.data.network.MoviesApi
import com.juacodev.fullmovies.data.network.ServerUrls.BASE_URL
import com.juacodev.fullmovies.data.network.repository.RemoteMoviesRepository
import com.juacodev.fullmovies.data.network.repository.RemoteRecomendationsMoviesRepository
import com.juacodev.fullmovies.data.network.repository.RemoteTrailersRepository
import com.juacodev.fullmovies.data.network.repository.RemoteUpCommingMoviesRepository
import com.juacodev.fullmovies.utils.Baseinterceptor
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {
    @Singleton
    @Provides
    fun provideRetrofit(): Retrofit {
        val client = OkHttpClient.Builder()
            .addInterceptor(Baseinterceptor())
            .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
            .build()
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
    @Singleton
    @Provides
    fun provideMovieApiClient(retrofit: Retrofit): MoviesApi = retrofit.create(MoviesApi::class.java)

    @Singleton
    @Provides
    fun providesGetTopRatedMovies(moviesApi: MoviesApi) = RemoteMoviesRepository(moviesApi)

    @Singleton
    @Provides
    fun providesGetUpcommingMovies(moviesApi: MoviesApi) = RemoteUpCommingMoviesRepository(moviesApi)

    @Singleton
    @Provides
    fun providesGetRecomendationsMovies(moviesApi: MoviesApi) = RemoteRecomendationsMoviesRepository(moviesApi)

    @Singleton
    @Provides
    fun providesGetVideos(moviesApi: MoviesApi) = RemoteTrailersRepository(moviesApi)

}