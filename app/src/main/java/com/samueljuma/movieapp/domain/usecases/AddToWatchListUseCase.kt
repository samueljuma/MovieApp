package com.samueljuma.movieapp.domain.usecases

import com.samueljuma.movieapp.data.model.MovieToWatch
import com.samueljuma.movieapp.domain.repositories.MovieRepository

class AddToWatchListUseCase(
    private val movieRepository: MovieRepository
) {
    suspend fun execute(movie: MovieToWatch) = movieRepository.addToWatchList(movie)
}