package com.samueljuma.movieapp.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@Entity(tableName = "popular_movies")
@JsonClass(generateAdapter = true) // tells Moshi to generate an adapter for this class. Moshi uses these adapters to convert JSON to objects, and vice versa.
data class Movie(

    @PrimaryKey
    @Json(name = "id") val id: Int,

    @Json(name = "title") val title: String,

    @Json(name = "original_language") val original_language: String,

    @Json(name = "overview") val overview: String,

    @Json(name = "poster_path") val poster_path: String,

    @Json(name = "release_date") val release_date: String

)
