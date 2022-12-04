package com.juacodev.fullmovies.domain

import com.juacodev.fullmovies.data.database.ClasificateMovies
import com.juacodev.fullmovies.data.database.MoviesDao
import com.juacodev.fullmovies.di.LocalModule
import com.juacodev.fullmovies.domain.models.DMovie
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class LocalRepository @Inject constructor(
    private val moviesDao: MoviesDao
) {
    suspend fun getMoviesByClasificate(limit:Int=20,offset:Int,clasificateMovies: ClasificateMovies) =
        moviesDao.getMoviesByClasificate(clasificateMovies.ordinal,limit,offset)

    suspend fun getMovieById(id:Int) = moviesDao.getMovieById(id)


    suspend fun insertAllMovies(movies:List<DMovie>,clasificateMovies: ClasificateMovies){
        val dataEntity=movies.map {
            it.clasificateMovies=clasificateMovies.ordinal
            it.toEntity()
        }
        moviesDao.insertAllMovies(dataEntity)
    }
}