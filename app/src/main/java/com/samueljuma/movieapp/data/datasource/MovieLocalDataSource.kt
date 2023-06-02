package com.samueljuma.movieapp.data.datasource

import com.samueljuma.movieapp.data.model.Movie

interface MovieLocalDataSource {

    suspend fun getMoviesFromDB(): List<Movie>

    suspend fun saveMoviesToDB(movies: List<Movie>)

    suspend fun clearAllFromDB()
}