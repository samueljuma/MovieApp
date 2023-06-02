package com.samueljuma.movieapp.data.datasource

import com.samueljuma.movieapp.data.model.Movie

interface MovieCacheDataSource {

    suspend fun getMoviesFromCache(): List<Movie>

    suspend fun saveMoviesToCache(movies: List<Movie>)
}