package com.samueljuma.movieapp.utils

import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.samueljuma.movieapp.data.model.Movie

@BindingAdapter("imageUrl")
fun ImageView.setMovieImage(movie: Movie?){
    movie?.let {
        Glide.with(context)
            .load("https://image.tmdb.org/t/p/w500"+it.poster_path)
            .into(this)
    }
}

@BindingAdapter("movieTitle")
fun TextView.setMovieTitle(movie: Movie?){
    movie?.let {
        text = it.title
    }
}