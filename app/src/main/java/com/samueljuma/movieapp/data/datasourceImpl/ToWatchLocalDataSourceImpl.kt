package com.samueljuma.movieapp.data.datasourceImpl

import com.samueljuma.movieapp.data.datasource.ToWatchLocalDataSource
import com.samueljuma.movieapp.data.db.ToWatchDao
import com.samueljuma.movieapp.data.model.MovieToWatch

class ToWatchLocalDataSourceImpl(
    private val toWatchDao: ToWatchDao
) : ToWatchLocalDataSource {
    override suspend fun addToWatchList(movie: MovieToWatch) {
        toWatchDao.saveToWatchMovie(movie)
    }

    override suspend fun removeFromWatchList(movieId: Int) {
        toWatchDao.deleteMovie(movieId)
    }

    override suspend fun getWatchList(): List<MovieToWatch> {
        return toWatchDao.getAllToWatchMovies()
    }

    override suspend fun deleteAllFromWatchList() {
        toWatchDao.deleteAllMovies()
    }

    override suspend fun getToWatchMovie(movieId: Int): MovieToWatch? {
        return toWatchDao.getToWatchMovieById(movieId)
    }

    override suspend fun isMovieInToWatchList(movieId: Int): Boolean {
        return toWatchDao.isMovieInToWatchList(movieId)
    }


}