package com.samueljuma.movieapp.presentation.ui.moviedetails

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.samueljuma.movieapp.data.model.Movie
import dagger.hilt.android.lifecycle.HiltViewModel

//@HiltViewModel : No need since we are not injecting anything
class MovieDetailsViewModel: ViewModel() {
    private val _movie = MutableLiveData<Movie>()
    val movie: LiveData<Movie>
        get() = _movie

    fun setMovie(movie: Movie) {
        _movie.value = movie
    }
}