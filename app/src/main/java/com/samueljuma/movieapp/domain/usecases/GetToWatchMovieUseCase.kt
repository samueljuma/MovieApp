package com.samueljuma.movieapp.domain.usecases

import com.samueljuma.movieapp.data.model.Movie
import com.samueljuma.movieapp.data.model.MovieToWatch
import com.samueljuma.movieapp.domain.repositories.MovieRepository

class GetToWatchMovieUseCase(
    private val movieRepository: MovieRepository) {
    suspend fun execute(movieId:Int) = movieRepository.getToWatchMovie(movieId)

}