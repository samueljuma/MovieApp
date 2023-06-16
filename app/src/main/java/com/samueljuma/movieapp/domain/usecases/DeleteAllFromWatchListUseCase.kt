package com.samueljuma.movieapp.domain.usecases

import com.samueljuma.movieapp.domain.repositories.MovieRepository

class DeleteAllFromWatchListUseCase(
    private val movieRepository: MovieRepository) {
    suspend fun execute() = movieRepository.removeAllFromWatchList()
}