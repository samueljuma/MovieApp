package com.samueljuma.movieapp.presentation.di

import com.samueljuma.movieapp.data.datasource.MovieLocalDataSource
import com.samueljuma.movieapp.data.datasource.ToWatchLocalDataSource
import com.samueljuma.movieapp.data.datasourceImpl.MovieLocalDataSourceImpl
import com.samueljuma.movieapp.data.datasourceImpl.ToWatchLocalDataSourceImpl
import com.samueljuma.movieapp.data.db.MovieDao
import com.samueljuma.movieapp.data.db.ToWatchDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class LocalDataModule {

    @Singleton
    @Provides
    fun provideMovieLocalDataSource(movieDao: MovieDao): MovieLocalDataSource {
        return MovieLocalDataSourceImpl(movieDao)
    }

    @Singleton
    @Provides
    fun provideToWatchLocalDataStore(toWatchDao: ToWatchDao): ToWatchLocalDataSource{
        return ToWatchLocalDataSourceImpl(toWatchDao)
    }
}