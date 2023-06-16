package com.samueljuma.movieapp.presentation.di

import com.samueljuma.movieapp.data.MovieRepositoryImpl
import com.samueljuma.movieapp.data.datasource.MovieCacheDataSource
import com.samueljuma.movieapp.data.datasource.MovieLocalDataSource
import com.samueljuma.movieapp.data.datasource.MovieRemoteDataSource
import com.samueljuma.movieapp.data.datasource.ToWatchLocalDataSource
import com.samueljuma.movieapp.domain.repositories.MovieRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RepositoryModule {

    @Singleton
    @Provides
    fun providesMovieRepository(
        movieRemoteDataSource: MovieRemoteDataSource,
        movieLocalDataSource: MovieLocalDataSource,
        movieCacheDataSource: MovieCacheDataSource,
        toWatchLocalDataSource: ToWatchLocalDataSource
        ): MovieRepository {

        return MovieRepositoryImpl(movieRemoteDataSource, movieLocalDataSource, movieCacheDataSource, toWatchLocalDataSource)
    }
}