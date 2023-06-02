package com.samueljuma.movieapp.domain.repositories

import com.samueljuma.movieapp.data.model.Movie

interface MovieRepository {

    suspend fun getAllMovies(): List<Movie>?

    suspend fun updateMovies(): List<Movie>?
}