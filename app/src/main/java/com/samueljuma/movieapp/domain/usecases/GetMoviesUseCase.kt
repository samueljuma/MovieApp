package com.samueljuma.movieapp.domain.usecases

import com.samueljuma.movieapp.data.model.Movie
import com.samueljuma.movieapp.domain.repositories.MovieRepository
import javax.inject.Inject

class GetMoviesUseCase (private val movieRepository: MovieRepository) {
    suspend fun execute(): List<Movie>? = movieRepository.getAllMovies()
}