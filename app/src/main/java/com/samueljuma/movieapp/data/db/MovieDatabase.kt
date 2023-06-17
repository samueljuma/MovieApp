package com.samueljuma.movieapp.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.samueljuma.movieapp.data.model.Movie
import com.samueljuma.movieapp.data.model.MovieToWatch
import com.samueljuma.movieapp.utils.DATABASE_NAME

@Database(entities = [Movie::class, MovieToWatch::class], version = 3, exportSchema = false)
abstract class MovieDatabase :RoomDatabase(){

    abstract val movieDao: MovieDao
    abstract val toWatchDao: ToWatchDao

    /*
    * Not needed. We use Dependency Injection for this case.
    * i.e we use the DatabaseModule
     */
//    companion object{
//        @Volatile
//        private var INSTANCE: MovieDatabase? = null
//
//        fun getInstance(context: Context): MovieDatabase {
//            //synchronized ensures only one thread of execution at a time
//            synchronized(this){
//                var instance = INSTANCE
//
//                if(instance == null){
//                    instance = Room.databaseBuilder(context.applicationContext,
//                    MovieDatabase::class.java, DATABASE_NAME)
//                        .fallbackToDestructiveMigration()
//                        .build()
//                    INSTANCE = instance
//                }
//                    return instance
//            }
//
//        }
//    }
}