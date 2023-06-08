package com.samueljuma.movieapp.data

import android.util.Log
import com.samueljuma.movieapp.data.datasource.MovieCacheDataSource
import com.samueljuma.movieapp.data.datasource.MovieLocalDataSource
import com.samueljuma.movieapp.data.datasource.MovieRemoteDataSource
import com.samueljuma.movieapp.data.model.Movie
import com.samueljuma.movieapp.domain.repositories.MovieRepository


/**
 * Connects the Data Layer with the Domain Layer
 * Implements the Repository interface in the domain layer
 */
class MovieRepositoryImpl (
    private val movieRemoteDataSource: MovieRemoteDataSource,
    private val movieLocalDataSource: MovieLocalDataSource,
    private val movieCacheDataSource: MovieCacheDataSource
) : MovieRepository{

    override suspend fun getAllMovies(): List<Movie>? {
        return getMoviesFromCache()
    }


    override suspend fun updateMovies(): List<Movie>? {
        val newListOfMovies = getMoviesFromAPI()
        movieLocalDataSource.clearAllFromDB()
        movieLocalDataSource.saveMoviesToDB(newListOfMovies)
        movieCacheDataSource.saveMoviesToCache(newListOfMovies)
        return newListOfMovies
    }


    /**
     * Gets Movies from the remote datasource (API)
     */
    private suspend fun getMoviesFromAPI(): List<Movie> {
        lateinit var movieList: List<Movie>

        try{
            val response = movieRemoteDataSource.getMovies()
            val body = response.body()
            if(body != null){
                movieList = body.movies
            }
        }catch (exception: java.lang.Exception){
            Log.e("Tagy", "Failed to get movies from API", exception)
        }
        return movieList
    }

    /**
     * Gets Movies from the local database
     */
    private suspend fun getMoviesFromRoom(): List<Movie>{
        lateinit var movieList: List<Movie>
        try{
            movieList = movieLocalDataSource.getMoviesFromDB()
        }catch (exception: Exception){

        }
        //Gets Movies from db if not empty, else gets movies from API
        return if(movieList.isNotEmpty()){
            movieList
        }else{
            movieList = getMoviesFromAPI()
            movieLocalDataSource.saveMoviesToDB(movieList)
            movieList
        }
    }

    /**
     * Gets Movies from the cache if any
     */
    private suspend fun getMoviesFromCache(): List<Movie>? {
        lateinit var movieList: List<Movie>

        try{
            movieList = movieCacheDataSource.getMoviesFromCache()
        }catch (exception: Exception){

        }
        //Gets Movies from cache if not empty, else gets movies from Room
        return if(movieList.isNotEmpty()){
            movieList
        }else{
            movieList = getMoviesFromRoom()
            movieCacheDataSource.saveMoviesToCache(movieList)
            movieList
        }
    }

}