package com.samueljuma.movieapp.data.datasource

import com.samueljuma.movieapp.data.model.MovieList
import retrofit2.Response

interface MovieRemoteDataSource {

    suspend fun getMovies(): Response<MovieList>
}