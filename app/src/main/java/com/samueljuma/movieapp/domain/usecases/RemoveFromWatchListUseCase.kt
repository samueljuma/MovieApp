package com.samueljuma.movieapp.domain.usecases

import com.samueljuma.movieapp.domain.repositories.MovieRepository

class RemoveFromWatchListUseCase(
    private val movieRepository: MovieRepository
) {
    suspend fun execute(movieId: Int) = movieRepository.removeFromWatchList(movieId)
}