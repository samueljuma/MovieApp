package com.samueljuma.movieapp.data.datasourceImpl

import com.samueljuma.movieapp.data.datasource.MovieLocalDataSource
import com.samueljuma.movieapp.data.db.MovieDao
import com.samueljuma.movieapp.data.model.Movie
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MovieLocalDataSourceImpl (
    private val movieDao: MovieDao
        ): MovieLocalDataSource{

    override suspend fun getMoviesFromDB(): List<Movie> {
        return movieDao.getAllMovies()
    }

    override suspend fun saveMoviesToDB(movies: List<Movie>) {
       // Run this in background
        CoroutineScope(Dispatchers.IO).launch {
            movieDao.saveMovies(movies)
        }
    }

    override suspend fun clearAllFromDB() {
        // Run this in background
        CoroutineScope(Dispatchers.IO).launch {
            movieDao.deleteAllMovies()
        }
    }
}