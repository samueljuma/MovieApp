package com.samueljuma.movieapp.data

import com.squareup.moshi.Json

data class MovieList(

    @Json(name = "results") val movies: List<Movie>
)
