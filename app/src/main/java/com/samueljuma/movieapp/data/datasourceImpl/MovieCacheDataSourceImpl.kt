package com.samueljuma.movieapp.data.datasourceImpl

import com.samueljuma.movieapp.data.datasource.MovieCacheDataSource
import com.samueljuma.movieapp.data.model.Movie

class MovieCacheDataSourceImpl: MovieCacheDataSource {

    private var movieList = ArrayList<Movie>()

    override suspend fun getMoviesFromCache(): List<Movie> {
        return movieList
    }

    override suspend fun saveMoviesToCache(movies: List<Movie>) {
        movieList.clear()
        movieList = ArrayList(movies)
    }
}