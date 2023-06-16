package com.samueljuma.movieapp.presentation.ui.watchlist

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import com.samueljuma.movieapp.data.model.MovieToWatch
import com.samueljuma.movieapp.domain.usecases.AddToWatchListUseCase
import com.samueljuma.movieapp.domain.usecases.DeleteAllFromWatchListUseCase
import com.samueljuma.movieapp.domain.usecases.GetWatchListUseCase
import com.samueljuma.movieapp.domain.usecases.RemoveFromWatchListUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WatchListViewModel @Inject constructor(
    private val addToWatchListUseCase: AddToWatchListUseCase,
    private val removeFromWatchListUseCase: RemoveFromWatchListUseCase,
    private val deleteAllFromWatchListUseCase: DeleteAllFromWatchListUseCase,
    private val getWatchListUseCase: GetWatchListUseCase
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

    fun removeFromWatchList(movieToWatch: MovieToWatch) {
        viewModelScope.launch {
            removeFromWatchListUseCase.execute(movieToWatch.id)
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


}