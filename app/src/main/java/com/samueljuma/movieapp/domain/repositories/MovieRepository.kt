package com.samueljuma.movieapp.domain.repositories

import com.samueljuma.movieapp.data.model.Movie
import com.samueljuma.movieapp.data.model.MovieToWatch

interface MovieRepository {

    suspend fun getAllMovies(): List<Movie>?

    suspend fun updateMovies(): List<Movie>?

    suspend fun addToWatchList(movie: MovieToWatch)

    suspend fun removeFromWatchList(movieId: Int)

    suspend fun getWatchList(): List<MovieToWatch>

    suspend fun removeAllFromWatchList()

}