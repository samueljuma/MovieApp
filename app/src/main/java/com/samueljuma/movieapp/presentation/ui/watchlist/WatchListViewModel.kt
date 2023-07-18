package com.samueljuma.movieapp.presentation.ui.watchlist

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import com.samueljuma.movieapp.data.model.Movie
import com.samueljuma.movieapp.data.model.MovieToWatch
import com.samueljuma.movieapp.domain.usecases.AddToWatchListUseCase
import com.samueljuma.movieapp.domain.usecases.DeleteAllFromWatchListUseCase
import com.samueljuma.movieapp.domain.usecases.GetToWatchMovieUseCase
import com.samueljuma.movieapp.domain.usecases.GetWatchListUseCase
import com.samueljuma.movieapp.domain.usecases.IsMovieInToWatchListUseCase
import com.samueljuma.movieapp.domain.usecases.RemoveFromWatchListUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WatchListViewModel @Inject constructor(
    private val addToWatchListUseCase: AddToWatchListUseCase,
    private val removeFromWatchListUseCase: RemoveFromWatchListUseCase,
    private val deleteAllFromWatchListUseCase: DeleteAllFromWatchListUseCase,
    private val getWatchListUseCase: GetWatchListUseCase,
    private val getToWatchMovieUseCase: GetToWatchMovieUseCase,
    private val isMovieInToWatchListUseCase: IsMovieInToWatchListUseCase
) : ViewModel() {

    fun getWatchList() = liveData {
        val toWatchList = getWatchListUseCase.execute()
        emit(toWatchList)
    }


    fun addToWatchList(movieToWatch: MovieToWatch) {
        viewModelScope.launch {
                addToWatchListUseCase.execute(movieToWatch)
        }

    }

    fun getToWatchMovie(movieId: Int) {
        viewModelScope.launch {
            getToWatchMovieUseCase.execute(movieId)
        }
    }



    fun deleteAllFromWatchList() {
        viewModelScope.launch {
            deleteAllFromWatchListUseCase.execute()
        }
    }


    private val _movieClicked = MutableLiveData<MovieToWatch?>()
    val movieClicked: LiveData<MovieToWatch?>
        get() = _movieClicked

    fun onMovieClicked(movieToWatch: MovieToWatch) {
        _movieClicked.value = movieToWatch
    }

    fun movieToToWatchMovie(movie: Movie): MovieToWatch {
        return MovieToWatch(
            movie.id,
            movie.title,
            movie.original_language,
            movie.overview,
            movie.poster_path,
            movie.release_date,
            System.currentTimeMillis()
        )
    }

    val isMovieInWatchList: MutableLiveData<Boolean> = MutableLiveData()

    fun isMovieInToWatchList(movieId: Int) {
        viewModelScope.launch {
            val result = isMovieInToWatchListUseCase.execute(movieId)
           isMovieInWatchList.postValue(result)
        }
    }

    private val _deleteMovieToWatch = MutableLiveData<MovieToWatch?>()

    val deleteMovieToWatch: LiveData<MovieToWatch?>
        get() = _deleteMovieToWatch
    fun onClickDeleteMovieToWatch(movieToWatch: MovieToWatch){
        _deleteMovieToWatch.value = movieToWatch
    }
    fun removeFromWatchList(movieToWatch: MovieToWatch) {
        viewModelScope.launch {
            removeFromWatchListUseCase.execute(movieToWatch.id)
        }
    }

}