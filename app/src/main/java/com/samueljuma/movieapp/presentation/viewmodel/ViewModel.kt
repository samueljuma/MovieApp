package com.samueljuma.movieapp.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.samueljuma.movieapp.data.model.Movie
import com.samueljuma.movieapp.domain.usecases.GetMoviesUseCase
import com.samueljuma.movieapp.domain.usecases.UpdateMoviesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ViewModel @Inject constructor(
    private val getMoviesUseCase: GetMoviesUseCase,
    private val updateMoviesUseCase: UpdateMoviesUseCase
): ViewModel() {

    fun getMovies() = liveData {
        val movieList = getMoviesUseCase.execute()
        emit(movieList)
    }

    fun updateMovies() = liveData {
        val movieList = updateMoviesUseCase.execute()
        emit(movieList)
    }

    /**
     * Handles Navigation to Movie Details Fragment
     */
    private val _navigateToMovieDetails = MutableLiveData<Movie?>()
    val navigateToMovieDetails: LiveData<Movie?>
        get() = _navigateToMovieDetails

    fun onMovieClicked(movie: Movie){
        _navigateToMovieDetails.value =movie
    }

    fun doneNavigatingToMovieDetails(){
        _navigateToMovieDetails.value = null
    }
}