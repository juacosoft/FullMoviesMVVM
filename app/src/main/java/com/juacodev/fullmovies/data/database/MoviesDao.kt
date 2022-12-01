package com.juacodev.fullmovies.data.database

import androidx.room.Dao
import androidx.room.Query
import com.juacodev.fullmovies.data.database.entities.MovieModel

@Dao
interface MoviesDao {
    @Query("SELECT * FROM movies")
    suspend fun getAllMovies(): List<MovieModel>
}