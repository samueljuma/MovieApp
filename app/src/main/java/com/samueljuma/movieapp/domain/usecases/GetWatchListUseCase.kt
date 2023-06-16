package com.samueljuma.movieapp.domain.usecases

import com.samueljuma.movieapp.data.model.MovieToWatch
import com.samueljuma.movieapp.domain.repositories.MovieRepository

class GetWatchListUseCase(
    private val movieRepository: MovieRepository
) {
    suspend fun execute(): List<MovieToWatch> = movieRepository.getWatchList()
}