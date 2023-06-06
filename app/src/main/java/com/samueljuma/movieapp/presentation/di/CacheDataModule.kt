package com.samueljuma.movieapp.presentation.di

import com.samueljuma.movieapp.data.datasource.MovieCacheDataSource
import com.samueljuma.movieapp.data.datasourceImpl.MovieCacheDataSourceImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class CacheDataModule {

    @Provides
    @Singleton
    fun providesMovieCacheDataSource(): MovieCacheDataSource{
        return MovieCacheDataSourceImpl()
    }
}