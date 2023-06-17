package com.samueljuma.movieapp.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.samueljuma.movieapp.data.model.MovieToWatch

@Dao
interface ToWatchDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveToWatchMovie(movie: MovieToWatch)

    @Query("DELETE FROM to_watch_movies WHERE id = :id")
    suspend fun deleteMovie(id: Int)

    @Query("SELECT * FROM to_watch_movies ORDER BY  time_created DESC")
    suspend fun getAllToWatchMovies(): List<MovieToWatch>

    @Query("DELETE FROM to_watch_movies")
    suspend fun deleteAllMovies()
    @Query("SELECT * FROM to_watch_movies WHERE id = :movieId LIMIT 1")
    suspend fun getToWatchMovieById(movieId: Int): MovieToWatch?

    @Query("SELECT EXISTS (SELECT 1 FROM to_watch_movies WHERE id = :movieId)")
    suspend fun isMovieInToWatchList(movieId: Int): Boolean


}