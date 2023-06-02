package com.samueljuma.movieapp.data.model

import com.squareup.moshi.Json

data class MovieList(

    @Json(name = "results") val movies: List<Movie>
)
