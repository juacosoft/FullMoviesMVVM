package com.juacodev.fullmovies.data.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.juacodev.fullmovies.data.database.entities.MovieLocalModel

@Dao
interface MoviesDao {
    @Query("SELECT * FROM movies LIMIT :limit OFFSET :offset")
    suspend fun getAllMovies(
        limit: Int,
        offset: Int
    ): List<MovieLocalModel>
    @Insert(onConflict =  OnConflictStrategy.REPLACE)
    suspend fun insertAllMovies(movies: List<MovieLocalModel>): List<Long>

    @Query("SELECT * FROM movies WHERE id = :id")
    suspend fun getMovieById(id: Int): MovieLocalModel

    @Query("SELECT * FROM movies WHERE clasificateMovies = :clasificateMovies LIMIT :limit OFFSET :offset")
    suspend fun getMoviesByClasificate(
        clasificateMovies: Int,
        limit: Int,
        offset: Int
    ): List<MovieLocalModel>

    @Query("DELETE FROM movies")
    suspend fun deleteAllMovies()
}