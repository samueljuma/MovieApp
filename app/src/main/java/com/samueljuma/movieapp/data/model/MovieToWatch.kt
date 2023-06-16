package com.samueljuma.movieapp.data.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Entity(tableName = "to_watch_movies")
@Parcelize
data class MovieToWatch(

    @PrimaryKey
    val id: Int,

    val title: String,

    val original_language: String,

    val overview: String,

    val poster_path: String,

    val release_date: String

) : Parcelable
