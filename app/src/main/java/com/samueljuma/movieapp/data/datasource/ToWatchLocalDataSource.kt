package com.samueljuma.movieapp.data.datasource

import com.samueljuma.movieapp.data.model.MovieToWatch

interface ToWatchLocalDataSource {

    suspend fun addToWatchList(movie: MovieToWatch)
    suspend fun removeFromWatchList(movieId: Int)
    suspend fun getWatchList(): List<MovieToWatch>
    suspend fun  deleteAllFromWatchList()
    suspend fun getToWatchMovie(movieId: Int): MovieToWatch?
    suspend fun isMovieInToWatchList(movieId: Int): Boolean
}