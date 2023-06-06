package com.samueljuma.movieapp.presentation.di

import android.content.Context
import androidx.room.Room
import com.samueljuma.movieapp.data.db.MovieDao
import com.samueljuma.movieapp.data.db.MovieDatabase
import com.samueljuma.movieapp.utils.DATABASE_NAME
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule {

    @Singleton
    @Provides
    //@ApplicationContext provides a way to safely inject and use a Context object without worrying about lifecycle-related memory leaks.
    fun provideMovieDatabaseInstance(@ApplicationContext context: Context): MovieDatabase {
        return Room.databaseBuilder(
            context,
            MovieDatabase::class.java,
            DATABASE_NAME)
            .fallbackToDestructiveMigration()
            .build()
    }

    @Singleton
    @Provides
    fun provideMovieDao(movieDatabase: MovieDatabase): MovieDao{
        return movieDatabase.movieDao
    }
}