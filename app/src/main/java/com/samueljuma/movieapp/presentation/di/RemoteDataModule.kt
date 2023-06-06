package com.samueljuma.movieapp.presentation.di

import com.samueljuma.movieapp.BuildConfig
import com.samueljuma.movieapp.data.api.TMDBService
import com.samueljuma.movieapp.data.datasource.MovieRemoteDataSource
import com.samueljuma.movieapp.data.datasourceImpl.MovieRemoteDataSourceImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RemoteDataModule {

    @Singleton
    @Provides
    fun provideApiKey(): String{
        return BuildConfig.API_KEY
    }

    @Singleton
    @Provides
    fun provideMovieRemoteDataSource(
        tmdbService: TMDBService,
        apiKey: String
    ): MovieRemoteDataSource{
        return MovieRemoteDataSourceImpl(tmdbService, apiKey)
    }
}