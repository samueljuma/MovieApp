package com.samueljuma.movieapp.presentation.di

import com.samueljuma.movieapp.domain.repositories.MovieRepository
import com.samueljuma.movieapp.domain.usecases.AddToWatchListUseCase
import com.samueljuma.movieapp.domain.usecases.DeleteAllFromWatchListUseCase
import com.samueljuma.movieapp.domain.usecases.GetMoviesUseCase
import com.samueljuma.movieapp.domain.usecases.GetToWatchMovieUseCase
import com.samueljuma.movieapp.domain.usecases.GetWatchListUseCase
import com.samueljuma.movieapp.domain.usecases.IsMovieInToWatchListUseCase
import com.samueljuma.movieapp.domain.usecases.RemoveFromWatchListUseCase
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

    @Provides
    @Singleton
    fun provideAddToWatchListUseCase(
        movieRepository: MovieRepository
    ): AddToWatchListUseCase{
        return AddToWatchListUseCase(movieRepository)
    }

    @Provides
    @Singleton
    fun provideDeleteAllFromWatchListUseCase(
        movieRepository: MovieRepository
    ): DeleteAllFromWatchListUseCase{
        return DeleteAllFromWatchListUseCase(movieRepository)
    }
    @Provides
    @Singleton
    fun provideGetWatchListUseCase(
        movieRepository: MovieRepository
    ): GetWatchListUseCase{
        return GetWatchListUseCase(movieRepository)
    }

    @Provides
    @Singleton
    fun provideRemoveFromWatchListUseCase(
        movieRepository: MovieRepository
    ): RemoveFromWatchListUseCase{
        return RemoveFromWatchListUseCase(movieRepository)
    }

    @Singleton
    @Provides
    fun provideGetToWatchMovieUseCase(
        movieRepository: MovieRepository
    ): GetToWatchMovieUseCase{
        return GetToWatchMovieUseCase(movieRepository)
    }

    @Provides
    @Singleton
    fun provideIsMovieInToWatchListUseCase(
        movieRepository: MovieRepository
    ): IsMovieInToWatchListUseCase{
        return IsMovieInToWatchListUseCase(movieRepository)
    }

}