package com.samueljuma.movieapp.data.datasourceImpl

import com.samueljuma.movieapp.data.api.TMDBService
import com.samueljuma.movieapp.data.datasource.MovieRemoteDataSource
import com.samueljuma.movieapp.data.model.MovieList
import retrofit2.Response

class MovieRemoteDataSourceImpl(
    private val tmdbService: TMDBService,
    private val apiKey: String
) : MovieRemoteDataSource {

    override suspend fun getMovies(): Response<MovieList> = tmdbService.getPopularMovies(apiKey)
}