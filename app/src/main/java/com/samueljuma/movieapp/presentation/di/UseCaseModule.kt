package com.samueljuma.movieapp.presentation.di

import com.samueljuma.movieapp.domain.repositories.MovieRepository
import com.samueljuma.movieapp.domain.usecases.GetMoviesUseCase
import com.samueljuma.movieapp.domain.usecases.UpdateMoviesUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class UseCaseModule {

    @Provides
    @Singleton
    fun provideGetMoviesUseCase(
        movieRepository: MovieRepository
    ): GetMoviesUseCase{
        return GetMoviesUseCase(movieRepository)
    }

    @Provides
    @Singleton
    fun provideUpdateMoviesUseCase(
        movieRepository: MovieRepository
    ): UpdateMoviesUseCase{
        return UpdateMoviesUseCase(movieRepository)
    }

}