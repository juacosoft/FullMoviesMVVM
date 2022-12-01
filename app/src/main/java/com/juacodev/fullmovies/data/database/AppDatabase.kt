package com.juacodev.fullmovies.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.juacodev.fullmovies.data.database.entities.MovieModel
import com.juacodev.fullmovies.data.database.entities.ObjectConverters

@Database(
    entities = [MovieModel::class],
    version = 1,
    exportSchema = false
)
@TypeConverters(ObjectConverters::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun moviesDao(): MoviesDao
}